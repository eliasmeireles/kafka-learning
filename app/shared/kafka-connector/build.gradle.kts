import com.gradle.kts.build.configuration.implementation
import com.gradle.kts.kotlin.buildsource.jsonLogger
import com.gradle.kts.kotlin.buildsource.springWebFlux

plugins {
    id("submodule-source-plugin")
}

group = "com.eliasmeireles.kafkalearning.connector"
version = "1.0.0"

repositories {
    maven("https://packages.confluent.io/maven/")
}

dependencies {
    implementation(project(":model"))
    implementation(project(":protobuf"))

    implementation("io.confluent:kafka-protobuf-serializer:7.5.0")
    implementation("org.springframework.kafka:spring-kafka")
    implementation("org.apache.kafka:kafka-clients")

    springWebFlux()
    jsonLogger()
}

