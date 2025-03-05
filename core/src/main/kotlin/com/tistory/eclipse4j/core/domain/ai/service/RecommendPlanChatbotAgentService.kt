package com.tistory.eclipse4j.core.domain.ai.service

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import dev.langchain4j.model.openai.OpenAiChatModel
import dev.langchain4j.store.embedding.EmbeddingSearchRequest
import dev.langchain4j.store.embedding.filter.comparison.ContainsString
import dev.langchain4j.store.embedding.pgvector.PgVectorEmbeddingStore
import io.github.oshai.kotlinlogging.KotlinLogging
import com.tistory.eclipse4j.core.domain.ai.embedding.LangChain4jEmbeddingService
import com.tistory.eclipse4j.core.domain.ai.model.RecommendPlanResponse
import com.tistory.eclipse4j.core.domain.ai.model.UsageDataSizePerMonthResponse
import com.tistory.eclipse4j.core.domain.utils.toTextFormat
import org.springframework.stereotype.Service
import reactor.core.publisher.Operators.DeferredSubscription

@Service
class RecommendPlanChatbotAgentService(
    private val openAiChatModel: OpenAiChatModel,
    private val embeddingService: LangChain4jEmbeddingService,
    private val pgvectorEmbeddingStore: PgVectorEmbeddingStore
) {
    private val log = KotlinLogging.logger { }

    fun chatAi(query: String): UsageDataSizePerMonthResponse {
        val prompt = """
            고객이 '$query' 라고 본인의 인터넷 데이터 사용 패턴을 이야기했습니다.
            분석하여 한달 사용량을 설명(description)과 GB로 답변된 용량(perMonth) 로 제공하고 결과는 JSON 로 알려줍니다.
            이때 json 백틱 은 포함하지 않습니다.
            답변은 기본 한글입니다.
            - description: TEXT 한달동안 사용 예상된 데이터 용량 사이즈.
            - perMonth: LONG GB 단위의 용량
            - calculationDetails: TEXT 계산 상세
        """.trimIndent()
        val messageJsonText = openAiChatModel.chat(prompt).trimIndent()
        log.info { messageJsonText }
        return jacksonObjectMapper().readValue(messageJsonText, object : TypeReference<UsageDataSizePerMonthResponse>() {})
    }

    fun generateResponse(context: String, sizePerMonthly: String, subscription: String): List<RecommendPlanResponse> {
        val prompt = """
            고객이 한달 동안 사용하는 데이터 사용량이 $sizePerMonthly  이정도라고 이야기 했습니다.
            $subscription
            이 사용 용량을 기준으로 한달간 사용 가능한 요금제를 아래 요금제 데이터에서 선택해주세요.


            --- 요금제 데이터 ---
            $context
            ------------------

            추천 결과를 3개 정도 반환하고,
            TYPE 은 최적 1개 추천 best 이고 best 요금제 보다 한 단계 높은 요금제 ( type 은 high ) 하나와 한단계 낮은 요금제 ( type 은 low ) 하나를 추천합니다.

            아래 JSON 형식으로 반환하고 리스트형태 입니다.
            이때 json 백틱 은 포함하지 않습니다.
            답변은 기본 한글입니다.
            - type: best, high, low 구분
            - recommendPlan: TEXT 요금제명
            - recommendPlanInfo: TEXT 요금제의 기본 정보 ( 기본 제공량, 추가 제공량, QOS 등 ) 를 자연스럽게 표현.
            - recommendReasons: TEXT 추천 사유이며, 고객이 사용패턴으로 이용시 한달간 총 몇 GB를 사용하는지 보여주고, 요금제 추천 사유를 보여준다.
        """.trimIndent()
        val messageJsonText = openAiChatModel.chat(prompt)
        log.info { messageJsonText }
        return jacksonObjectMapper().readValue(messageJsonText, object : TypeReference<List<RecommendPlanResponse>>() {})
    }

    fun recommendPlanForSizeChatAi(size: Long): List<RecommendPlanResponse> {
        return this.recommendPlanForSizeChatAi(size, null)
    }

    fun recommendPlanForSizeChatAi(size: Long, subscription: String?): List<RecommendPlanResponse> {
        if( size > 100L ) return listOf( RecommendPlanResponse.unLimitPlan() )
        if( (size - 6L) == 0L ) return listOf( RecommendPlanResponse.emptyResultMessage() )
        val queryEmbedding = embeddingService.generateEmbedding("한달 총 사용량 ${size.toString()} GB")
        val searchRequest = EmbeddingSearchRequest.builder()
            .queryEmbedding(queryEmbedding)
            .maxResults(50)
            .build()
        val searchResult = pgvectorEmbeddingStore.search(searchRequest)
        val filteredResults = searchResult.matches()
            .filter {
                it.score() >= 0.50 // 유사도가 대충 비슷하면 ( 반 )
            }
            .filter {
                val metadata = it.embedded().metadata()
                val totalData = metadata.getLong("perMonthly")
                totalData != null && totalData >= (size - 6)
            }
            .sortedBy {
                it.embedded().metadata().getLong("perMonthly")
            }
            .take(50) // 상위 5개만 사용
        if (filteredResults.isNotEmpty()) {
            val bestMatch = filteredResults.joinToString("\n\n") { it.embedded().text() }
            return generateResponse(bestMatch, size.toString(), buildSubDescription(subscription))
        }
        return listOf( RecommendPlanResponse.emptyResultMessage() )
    }

    private fun buildSubDescription(subscription: String?): String {
        if (subscription != null) {
            return """
                추가적으로 고객의 최근 사용량 정보는 아래와 같습니다.
                -----
                $subscription
                -----
            """.trimIndent()
        }
        return "\n"
    }
}
