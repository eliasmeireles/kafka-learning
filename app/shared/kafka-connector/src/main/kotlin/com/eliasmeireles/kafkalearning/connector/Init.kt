package com.eliasmeireles.kafkalearning.connector

import com.eliasmeireles.kafkalearning.connector.properties.ConnectorProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@EnableConfigurationProperties(value = [ConnectorProperties::class])
class Init
