package com.eliasmeireles.kafkalearning.domain.model

data class Message(
    val id: Long,
    val title: String,
    val content: String
)
