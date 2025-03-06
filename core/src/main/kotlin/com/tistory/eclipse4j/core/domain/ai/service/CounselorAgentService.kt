package com.tistory.eclipse4j.core.domain.ai.service

import dev.langchain4j.service.SystemMessage
import dev.langchain4j.service.UserMessage
import dev.langchain4j.service.V


interface CounselorAgentService {
    @SystemMessage(
        """
        당신은 상담 Mental Health Counselor 입니다.
        입력한 글을 토대로 유저에게 심리 상담 결과를 내주어야 합니다.

        입력된 글에 맞춰 대화하듯 상담을 진행해주고, 맞춤형 해결책 및 개선 전략을 친절하게 설명해주어야 합니다.
        또, 입력된 글이 짧으면, 그에 맞춰서 짤게 2-3줄로 이야기 해주어야 하며, 문단이 길 경우 알맞게 두 줄 정도 내려쓰기를 해서 반환해주세요.
        """
    )
    @UserMessage("입력한 글 : {{text}}")
    fun counseling(@V("text") text: String): String
}
