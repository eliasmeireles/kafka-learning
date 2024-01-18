package com.eliasmeireles.kafkalearning.domain.producer

import com.eliasmeireles.kafkalearning.domain.model.Message
import com.eliasmeireles.kafkalearning.domain.publisher.MessagePublisher
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Service

@Service
class MessageProducer(
    private val messagePublisher: MessagePublisher
) : ApplicationRunner {

    private fun createMessage() {
        val message = Message(
            id = System.currentTimeMillis(),
            title = "Title:: ${System.currentTimeMillis()}",
            content = "Content:: ${System.currentTimeMillis()}"
        )

        messagePublisher.publish(message)

        Thread.sleep(250)

        createMessage()
    }

    override fun run(args: ApplicationArguments?) {
        createMessage()
    }

}
