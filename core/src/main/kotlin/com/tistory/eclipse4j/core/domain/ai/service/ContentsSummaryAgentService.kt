package com.tistory.eclipse4j.core.domain.ai.service

import dev.langchain4j.service.SystemMessage
import dev.langchain4j.service.UserMessage
import dev.langchain4j.service.V

interface ContentsSummaryAgentService {
    @SystemMessage(
        """
        당신은 텍스트 분석을 수행하는 비서입니다. 입력된 텍스트를 읽고 다음과 같이 답변을 진행합니다.
        1. 입력된 텍스트에 대해 간결한 요약을 제공하세요.
        2. 입력된 텍스트의 감정(sentiment)을 긍정(Positive), 부정(Negative), 중립(Neutral) 중 하나로 분석하세요
        3. 적용 가능하다면 추가적인 통찰(additionalInsights)도 선택적으로 제공하세요.
        4. 문장에서 중요한 키워드를 추출하여 공백 없이 하나의 단어로 연결된 태그 형태로 변환해 주세요.
           결과는 3개의 태그로 구성되며, 가장 핵심적인 주제와 관련된 단어를 선택해 주세요.

        답변은 모두 한글로 진행되며, 꼭 유저가 입력된 테스트만을 데이터로 삼아 답변합니다.
        요약, 감정 분석 결과, 추가적인 통찰 정보, 태그를 포함한 정보를 아래 변수면에 맞춰 JSON 개체로 응답하며 json 백틱 은 포함하지 않습니다.

        - summary 요약된 텍스트를 포함하는 문자열 중심이 되는 회사나 인물을 포함해서 3줄이상으로 요약.
        - sentiment 감정 분석 결과(긍정, 부정, 중립 중 하나).
        - additionalInsights 선택적 통찰을 포함하는 키-값 쌍의 맵(Map) 없으면 비어있는 Map()으로 반환.
        - tags 가장 핵심적인 주제와 관련된 단어 3개를 제공한다. 예시: [태그1, 태그2, 태그3] 리스트(List)
        """
    )
    @UserMessage("입력된 텍스트 : {{text}}")
    fun summarize(@V("text") text: String): String
}
