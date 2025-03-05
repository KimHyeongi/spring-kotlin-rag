package com.tistory.eclipse4j.client.web.example.request

data class ContentsReq(
    val contents: String? = null
) {
    companion object {
        fun init(): ContentsReq {
            return ContentsReq()
        }
    }
}
