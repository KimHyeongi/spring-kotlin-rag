package com.tistory.eclipse4j.core.domain.ai.embedding

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import dev.langchain4j.data.document.Metadata
import dev.langchain4j.data.segment.TextSegment
import dev.langchain4j.store.embedding.pgvector.PgVectorEmbeddingStore
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Service
import java.io.Serializable

@Service
class RecommendPlanChatbotVectorInitialService(
    private val embeddingService: LangChain4jEmbeddingService,
    private val pgvectorEmbeddingStore: PgVectorEmbeddingStore
) {
    fun initializePlanEmbedding() {
        val tariffData = setOf(
            "요금제명 '59요금제'는 데이터 무제한으로 사용할 수 있습니다.",
            "요금제명 '42요금제'는 데이터 기본 50GB 와 추가 50GB 를 제공하므로 총 100GB 를 사용할 수 있습니다. 사용량을 모두 사용하였을 경우 QOS 는 1Mbps 로 이용할 수 있습니다. 가령 100GB로 720p 유튜브는 약 66시간, 1080p는 약33시간 볼 수 있습니다. 또한, 100GB로 보통음질(128kbps) 음악은 약 1,666시간 (69일) 정도 들을 수 있습니다. 데이터 소진 후 QOS(1Mbps) 상태에서는 144p~240p 유튜브, 128kbps 이하 음악 스트리밍이 가능합니다.",
            "요금제명 '41요금제'는 데이터 기본 41GB 와 추가 41GB 를 제공하므로 총 82GB 를  사용할 수 있습니다. 사용량을 모두 사용하였을 경우 QOS 는 1Mbps 로 이용할 수 있습니다. 가령 82GB로 720p 유튜브는 약 54시간, 1080p는 약 27시간 볼 수 있습니다. 또한, 82GB로 보통음질(128kbps) 음악은 약 1,366시간 (약 57일) 정도 들을 수 있습니다. 데이터 소진 후 QOS(1Mbps) 상태에서는 144p~240p 유튜브, 128kbps 이하 음악 스트리밍이 가능합니다.",
            "요금제명 '40요금제'는 데이터 기본 38GB 와 추가 38GB 를 제공하므로 총 76GB 를 사용할 수 있습니다. 사용량을 모두 사용하였을 경우 QOS 는 1Mbps 로 이용할 수 있습니다. 가령 76GB로 720p 유튜브는 약 50시간, 1080p는 약 25시간 볼 수 있습니다. 또한, 76GB로 보통음질(128kbps) 음악은 약 1,266시간 (약 53일) 정도 들을 수 있습니다. 데이터 소진 후 QOS(1Mbps) 상태에서는 144p~240p 유튜브, 128kbps 이하 음악 스트리밍이 가능합니다.",
            "요금제명 '39요금제'는 데이터 기본 36GB 와 추가 36GB 를 제공하므로 총 72GB 를 사용할 수 있습니다. 사용량을 모두 사용하였을 경우 QOS 는 1Mbps 로 이용할 수 있습니다. 가령 72GB로 720p 유튜브는 약 48시간, 1080p는 약 24시간 볼 수 있습니다. 또한, 72GB로 보통음질(128kbps) 음악은 약 1,200시간 (약 50일) 정도 들을 수 있습니다. 데이터 소진 후 QOS(1Mbps) 상태에서는 144p~240p 유튜브, 128kbps 이하 음악 스트리밍이 가능합니다.",
            "요금제명 '38요금제'는 데이터 기본 33GB 와 추가 33GB 를 제공하므로 총 66GB 를 사용할 수 있습니다. 사용량을 모두 사용하였을 경우 QOS 는 1Mbps 로 이용할 수 있습니다. 가령 66GB로 720p 유튜브는 약 44시간, 1080p는 약 22시간 볼 수 있습니다. 또한, 66GB로 보통음질(128kbps) 음악은 약 1,100시간 (약 46일) 정도 들을 수 있습니다. 데이터 소진 후 QOS(1Mbps) 상태에서는 144p~240p 유튜브, 128kbps 이하 음악 스트리밍이 가능합니다.",
            "요금제명 '37요금제'는 데이터 기본 31GB 와 추가 31GB 를 제공하므로 총 62GB 를 사용할 수 있습니다. 사용량을 모두 사용하였을 경우 QOS 는 1Mbps 로 이용할 수 있습니다. 가령 62GB로 720p 유튜브는 약 41시간, 1080p는 약 20시간 볼 수 있습니다. 또한, 62GB로 보통음질(128kbps) 음악은 약 1,033시간 (약 43일) 정도 들을 수 있습니다. 데이터 소진 후 QOS(1Mbps) 상태에서는 144p~240p 유튜브, 128kbps 이하 음악 스트리밍이 가능합니다.",
            "요금제명 '36요금제'는 데이터 기본 27GB 와 추가 27GB 를 제공하므로 총 54GB 를 사용할 수 있습니다. 사용량을 모두 사용하였을 경우 QOS 는 1Mbps 로 이용할 수 있습니다. 가령 54GB로 720p 유튜브는 약 36시간, 1080p는 약 18시간 볼 수 있습니다. 또한, 54GB로 보통음질(128kbps) 음악은 약 900시간 (약 37일) 정도 들을 수 있습니다. 데이터 소진 후 QOS(1Mbps) 상태에서는 144p~240p 유튜브, 128kbps 이하 음악 스트리밍이 가능합니다.",
            "요금제명 '35요금제'는 데이터 기본 25GB 와 추가 25GB 를 제공하므로 총 50GB 를 사용할 수 있습니다. 사용량을 모두 사용하였을 경우 QOS 는 1Mbps 로 이용할 수 있습니다. 가령 50GB로 720p 유튜브는 약 33시간, 1080p는 약 16시간 볼 수 있습니다. 또한, 50GB로 보통음질(128kbps) 음악은 약 833시간 (약 35일) 정도 들을 수 있습니다. 데이터 소진 후 QOS(1Mbps) 상태에서는 144p~240p 유튜브, 128kbps 이하 음악 스트리밍이 가능합니다.",
            "요금제명 '34요금제'는 데이터 기본 22GB 와 추가 22GB 를 제공하므로 총 44GB 를 사용할 수 있습니다. 사용량을 모두 사용하였을 경우 QOS 는 1Mbps 로 이용할 수 있습니다. 가령 44GB로 720p 유튜브는 약 29시간, 1080p는 약 14시간 볼 수 있습니다. 또한, 44GB로 보통음질(128kbps) 음악은 약 733시간 (약 31일) 정도 들을 수 있습니다. 데이터 소진 후 QOS(1Mbps) 상태에서는 144p~240p 유튜브, 128kbps 이하 음악 스트리밍이 가능합니다.",
            "요금제명 '33요금제'는 데이터 기본 20GB 와 추가 6GB 를 제공하므로 총 26GB 를 사용할 수 있습니다. 사용량을 모두 사용하였을 경우 QOS 는 1Mbps 로 이용할 수 있습니다. 가령 26GB로 720p 유튜브는 약 17시간, 1080p는 약 8시간 볼 수 있습니다. 또한, 26GB로 보통음질(128kbps) 음악은 약 433시간 (약 18일) 정도 들을 수 있습니다. 데이터 소진 후 QOS(1Mbps) 상태에서는 144p~240p 유튜브, 128kbps 이하 음악 스트리밍이 가능합니다.",
            "요금제명 '32요금제'는 데이터 기본 17GB 와 추가 6GB 를 제공하므로 총 23GB 를 사용할 수 있습니다. 사용량을 모두 사용하였을 경우 QOS 는 400Kbps 로 이용할 수 있습니다. 가령 23GB로 720p 유튜브는 약 15시간, 1080p는 약 7시간 볼 수 있습니다. 또한, 23GB로 보통음질(128kbps) 음악은 약 383시간 (약 16일) 정도 들을 수 있습니다. 데이터 소진 후 QOS(400Kbps) 상태에서는 144p 이하의 유튜브 영상 재생 및 128kbps 이하의 음악 스트리밍이 가능합니다. (단, 영상은 끊김이 있을 수 있으며, 음악 스트리밍은 원활합니다.)",
            "요금제명 '31요금제'는 데이터 기본 12GB 와 추가 5GB 를 제공하므로 총 17GB 를 사용할 수 있습니다. 사용량을 모두 사용하였을 경우 QOS 는 400Kbps 로 이용할 수 있습니다. 가령 17GB로 720p 유튜브는 약 11시간, 1080p는 약 5시간 볼 수 있습니다. 또한, 17GB로 보통음질(128kbps) 음악은 약 283시간 (약 12일) 정도 들을 수 있습니다. 데이터 소진 후 QOS(400Kbps) 상태에서는 144p 이하의 유튜브 영상 재생 및 128kbps 이하의 음악 스트리밍이 가능합니다. (단, 영상은 끊김이 있을 수 있으며, 음악 스트리밍은 원활합니다.)",
            "요금제명 '30요금제'는 데이터 기본 10GB 와 추가 5GB 를 제공하므로 총 15GB 를 사용할 수 있습니다. 사용량을 모두 사용하였을 경우 QOS 는 400Kbps 로 이용할 수 있습니다. 가령 15GB로 720p 유튜브는 약 10시간, 1080p는 약 5시간 볼 수 있습니다. 또한, 15GB로 보통음질(128kbps) 음악은 약 250시간 (약 10일) 정도 들을 수 있습니다. 데이터 소진 후 QOS(400Kbps) 상태에서는 144p 이하의 유튜브 영상 재생 및 128kbps 이하의 음악 스트리밍이 가능합니다. (단, 영상은 끊김이 있을 수 있으며, 음악 스트리밍은 원활합니다.)",
            "요금제명 '29요금제'는 데이터 기본 8GB 와 추가 5GB 를 제공하므로 총 13GB 를 사용할 수 있습니다. 사용량을 모두 사용하였을 경우 QOS 는 400Kbps 로 이용할 수 있습니다. 가령 13GB로 720p 유튜브는 약 8시간, 1080p는 약 4시간 볼 수 있습니다. 또한, 13GB로 보통음질(128kbps) 음악은 약 216시간 (약 9일) 정도 들을 수 있습니다. 데이터 소진 후 QOS(400Kbps) 상태에서는 144p 이하의 유튜브 영상 재생 및 128kbps 이하의 음악 스트리밍이 가능합니다. (단, 영상은 끊김이 있을 수 있으며, 음악 스트리밍은 원활합니다.)",
            "요금제명 '28요금제'는 데이터 기본 7GB 와 추가 5GB 를 제공하므로 총 12GB 를 사용할 수 있습니다. 사용량을 모두 사용하였을 경우 QOS 는 400Kbps 로 이용할 수 있습니다. 가령 12GB로 720p 유튜브는 약 8시간, 1080p는 약 4시간 볼 수 있습니다. 또한, 12GB로 보통음질(128kbps) 음악은 약 200시간 (약 8일) 정도 들을 수 있습니다. 데이터 소진 후 QOS(400Kbps) 상태에서는 144p 이하의 유튜브 영상 재생 및 128kbps 이하의 음악 스트리밍이 가능합니다. (단, 영상은 끊김이 있을 수 있으며, 음악 스트리밍은 원활합니다.)",
            "요금제명 '26요금제'는 데이터 기본 6GB 있다. 사용량을 모두 사용하였을 경우 QOS 는 400Kbps 로 이용할 수 있습니다. 가령 6GB로 720p 유튜브는 약 4시간, 1080p는 약 2시간 볼 수 있습니다. 또한, 6GB로 보통음질(128kbps) 음악은 약 100시간 (약 4일) 정도 들을 수 있습니다. 데이터 소진 후 QOS(400Kbps) 상태에서는 144p 이하의 유튜브 영상 재생 및 128kbps 이하의 음악 스트리밍이 가능합니다. (단, 영상은 끊김이 있을 수 있으며, 음악 스트리밍은 원활합니다.)",
        )
        val metadata = mapOf("x" to "x")
        tariffData.forEach { description ->
            val embedding = embeddingService.generateEmbedding(description)
            pgvectorEmbeddingStore.add(embedding, TextSegment.from(description, Metadata(metadata)))
        }
    }

    fun initializePlanEmbedding4FileData() {
        val objectMapper = jacksonObjectMapper()

        val resource = ClassPathResource("plan-specs.txt")
        val lines = resource.inputStream.bufferedReader().use { it.readLines() }
        val plans = lines.map { description ->
            val split = description.split("|").map { it.trim() }

            val planName = split[1] // 요금제명
            val jsonMetadata = split[2] // JSON 형식의 메타데이터
            val planInfo = split[3] // 설명
            // JSON 을 Map<String, Any> 형태로 변환
            val metadataMap: MutableMap<String, Any> = objectMapper.readValue(
                jsonMetadata,
                object : TypeReference<Map<String, Any>>() {}
            ).toMutableMap()
            metadataMap.forEach { (key, value) ->
                if (value is Map<*, *>) {
                    metadataMap[key] = objectMapper.writeValueAsString(value) // JSON 문자열 변환
                }
            }
            EmbeddingPlanData(
                planName = planName,
                metadata = metadataMap,
                planInfo = planInfo
            )
        }
        plans.forEach { plan ->
            val embedding = embeddingService.generateEmbedding(plan.planInfo)
//            val planMap = objectMapper.convertValue(description, object : TypeReference<Map<String, String>>() {})
//            pgvectorEmbeddingStore.add(embedding, TextSegment.from(description.planInfo, Metadata(planMap)))
            val metadataObject = Metadata(plan.metadata.mapValues { it.value.toString() })
            pgvectorEmbeddingStore.add(
                embedding,
                TextSegment.from(plan.planInfo, metadataObject)
            )

        }
    }
}

data class EmbeddingPlanData(
    val planName: String,
    val metadata: Map<String, Any>,
    val planInfo: String,
): Serializable
