package com.phibersoft.remoteph.helpers

class EventHandler {
    companion object {
        fun CreateMediaEvent(key: String, modifs: List<String>? = listOf()): List<Any?> {
            return listOf("action_keypress", key, modifs)
        }

        fun CreateLoLEvent(key: String, champion: String? = ""): List<Any?> {
            return listOf("lol", key, champion)
        }
    }
}