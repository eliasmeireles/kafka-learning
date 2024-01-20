import com.gradle.kts.build.configuration.fasterXmlJackson
import com.gradle.kts.build.configuration.implementation
import com.gradle.kts.kotlin.buildsource.*

plugins {
    id("source-plugin")
}

group = "com.eliasmeireles.kafkalearning"
version = "1.0.0"

repositories {
    maven("https://packages.confluent.io/maven/")
}

dependencies {
    implementation(project(":model"))
    implementation(project(":protobuf"))
    implementation(project(":kafka-connector"))

    implementation("io.confluent:kafka-protobuf-serializer:7.5.0")
    implementation("org.springframework.kafka:spring-kafka")
    implementation("org.apache.kafka:kafka-clients")

    implementation("com.google.protobuf:protobuf-java:3.16.3")
    implementation("io.grpc:grpc-protobuf:1.15.1")
    implementation("io.grpc:grpc-stub:1.60.0")

    logstashLogbackEncoderVersion()
    fasterXmlJackson()
    springJettyApi()
    springWebFlux()
    mappstruct()
    jsonLogger()
    passay()
    test()
}

