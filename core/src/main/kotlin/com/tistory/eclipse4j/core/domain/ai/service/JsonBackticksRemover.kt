package com.tistory.eclipse4j.core.domain.ai.service

class JsonBackticksRemover {
    companion object {
        fun removeBackticks(json: String): String {
            return json.replace("```json", "").replace("```", "")
        }
    }
}
