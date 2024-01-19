package com.eliasmeireles.kafkalearning.consumer

import com.eliasmeireles.kafkalearning.mapper.MessageMapper
import com.eliasmeireles.kafkalearning.mapper.parse
import com.google.protobuf.DynamicMessage
import com.softwareplace.jsonlogger.log.JsonLog
import com.softwareplace.jsonlogger.log.kLogger
import org.slf4j.event.Level
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.support.KafkaHeaders
import org.springframework.messaging.handler.annotation.Header
import org.springframework.stereotype.Service

@Service
class MessageConsumer(
    private val mapper: MessageMapper
) {

    @KafkaListener(
        id = "kafka-learning-consumer",
        topics = ["KAFKA_LEARNING"],
        groupId = "kafka-learning",
        autoStartup = "true"
    )
    fun listen(
        messageProto: DynamicMessage,
        @Header(KafkaHeaders.RECEIVED_TOPIC) topic: String,
        @Header(KafkaHeaders.RECEIVED_KEY) key: String
    ) {
        val message = mapper.parse(messageProto)

        JsonLog(kLogger).level(Level.INFO)
            .message("New message received")
            .add("message", message)
            .run()
    }
}
