package com.eliasmeireles.kafkalearning.connector.datasource

import com.eliasmeireles.kafkalearning.connector.properties.ConnectorProperties
import com.eliasmeireles.kafkalearning.protobuf.MessageProto
import io.confluent.kafka.serializers.protobuf.KafkaProtobufDeserializer
import io.confluent.kafka.serializers.protobuf.KafkaProtobufSerializer
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate

@Configuration
class KafkaDatasourceConfig(
    private val connectorProps: ConnectorProperties
) {

    fun config(): MutableMap<String, Any> {
        val props = HashMap<String, Any>()
        props[ProducerConfig.BOOTSTRAP_SERVERS_CONFIG] = connectorProps.bootstrapServers
        props[ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.java
        props[ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG] = KafkaProtobufSerializer::class.java
        props[ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java
        props[ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG] = KafkaProtobufDeserializer::class.java
        props["schema.registry.url"] = connectorProps.schemaRegistryUrl
        return props
    }

    @Bean
    fun producerFactory(): DefaultKafkaProducerFactory<String, MessageProto.Message> {
        return DefaultKafkaProducerFactory(config())
    }

    @Bean
    fun consumerFactory(): ConsumerFactory<String, MessageProto.Message> {
        return DefaultKafkaConsumerFactory(config())
    }

    @Bean
    fun kafkaListenerContainerFactory(default: DefaultKafkaConsumerFactory<String, MessageProto.Message>)
            : ConcurrentKafkaListenerContainerFactory<String, MessageProto.Message> {
        val factory = ConcurrentKafkaListenerContainerFactory<String, MessageProto.Message>()
        factory.consumerFactory = default
        return factory
    }

    @Bean
    fun kafkaTemplate(factory: DefaultKafkaProducerFactory<String, MessageProto.Message>)
            : KafkaTemplate<String, MessageProto.Message> {
        return KafkaTemplate(factory)
    }
}

