import com.gradle.kts.build.configuration.implementation
import com.gradle.kts.kotlin.buildsource.mappstruct
import com.gradle.kts.kotlin.buildsource.springWebFlux

plugins {
    id("submodule-source-plugin")
}

group = "com.eliasmeireles.kafkalearning.model"
version = "1.0.0"

dependencies {
    implementation(project(":protobuf"))
    implementation("com.google.protobuf:protobuf-java:3.16.3")

    springWebFlux()
    mappstruct()
}

