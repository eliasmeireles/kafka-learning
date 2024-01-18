package com.eliasmeireles.kafkalearning.domain.publisher

import com.eliasmeireles.kafkalearning.domain.model.Message
import com.eliasmeireles.kafkalearning.mapper.MessageMapper
import com.softwareplace.jsonlogger.log.JsonLog
import com.softwareplace.jsonlogger.log.kLogger
import org.slf4j.event.Level
import org.springframework.stereotype.Service

@Service
class MessagePublisher(
    private val mapper: MessageMapper
) {
    fun publish(message: Message) {
        val messageProto = mapper.parse(message);

        JsonLog(kLogger)
            .level(Level.INFO)
            .message("New message generated")
            .add("message", message)
            .run()
    }
}
