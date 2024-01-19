package com.eliasmeireles.kafkalearning.model

data class Message(
    val id: Long,
    val title: String,
    val content: String
)
