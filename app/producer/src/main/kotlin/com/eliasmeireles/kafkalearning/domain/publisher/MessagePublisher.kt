package com.eliasmeireles.kafkalearning.domain.publisher

import com.eliasmeireles.kafkalearning.mapper.MessageMapper
import com.eliasmeireles.kafkalearning.model.Message
import com.eliasmeireles.kafkalearning.protobuf.MessageProto
import com.softwareplace.jsonlogger.log.JsonLog
import com.softwareplace.jsonlogger.log.kLogger
import kotlinx.coroutines.suspendCancellableCoroutine
import org.slf4j.event.Level
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.support.SendResult
import org.springframework.stereotype.Service
import java.util.*
import java.util.concurrent.CompletableFuture
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

@Service
class MessagePublisher(
    private val mapper: MessageMapper,
    private val kafkaSender: KafkaTemplate<String, MessageProto.Message>,
) {

    suspend fun publish(message: Message) {
        val messageProto = mapper.parse(message);

        try {
            val future = kafkaSender.send("KAFKA_LEARNING", UUID.randomUUID().toString(), messageProto)
            val result = future.await()
            whenComplete(result)
        } catch (error: Exception) {
            JsonLog(this.kLogger)
                .level(Level.ERROR)
                .message("Failed to publish message")
                .add("message", message)
                .run()
        }

    }

    suspend fun whenComplete(result: SendResult<String, MessageProto.Message>) {
        JsonLog(this.kLogger)
            .level(Level.INFO)
            .message("New message published")
            .add("target", result.recordMetadata.toString())
            .add("messageKey", result.producerRecord.key())
            .run()
    }

    suspend fun <T> CompletableFuture<T>.await(): T = suspendCancellableCoroutine { cont ->
        whenComplete { result, exception ->
            if (exception == null) {
                cont.resume(result)
            } else {
                cont.resumeWithException(exception)
            }
        }
    }
}
