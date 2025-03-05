package com.tistory.eclipse4j.core.domain.ai.service

import dev.langchain4j.service.SystemMessage
import dev.langchain4j.service.UserMessage
import dev.langchain4j.service.V


interface ProfanityAgentService {
    @SystemMessage(
        """
        당신은 텍스트 분석을 수행하는 비서입니다.
        입력된 텍스트에서 비속어, 욕설, 비하 단어 등 비속어 단어 모두 검색하고,
        검색된 비속어 단어와 해당 비속어에 비속어 강도 1에서 ~ 5점 사이로 정리하고,
        비속어로 선정한 사유를 알려주세요.

        결과를 아래 키에 맞춰 JSON List 객체로 응답해주세요. 결과가 없는경우 반환값은 "" 입니다.
        이때 json 백틱 은 포함하지 않습니다.
        - profanityWord 비속어 단어
        - profanityWordScore 비속어의 강도 점수 (1~5)
        - profanityWordReasons 비속어 사유
        """
    )
    @UserMessage("요약 및 분석결과 {{text}}")
    fun find(@V("text") text: String): String
}
