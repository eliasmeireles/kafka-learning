package com.eliasmeireles.kafkalearning.domain.producer

import com.eliasmeireles.kafkalearning.domain.publisher.MessagePublisher
import com.eliasmeireles.kafkalearning.model.Message
import kotlinx.coroutines.runBlocking
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Service

@Service
class MessageProducer(
    private val messagePublisher: MessagePublisher
) : ApplicationRunner {

    private suspend fun createMessage() {
        val message = Message(
            id = System.currentTimeMillis(),
            title = "Title:: ${System.currentTimeMillis()}",
            content = "Content:: ${System.currentTimeMillis()}"
        )

        messagePublisher.publish(message)
        createMessage()
    }

    override fun run(args: ApplicationArguments?) {
        runBlocking { createMessage() }
    }

}
