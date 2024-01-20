package com.eliasmeireles.kafkalearning.connector.properties

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "spring.connector")
class ConnectorProperties {
    lateinit var bootstrapServers: String
    lateinit var schemaRegistryUrl: String
}
