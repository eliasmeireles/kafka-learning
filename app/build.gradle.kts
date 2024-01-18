import com.google.protobuf.gradle.proto
import com.gradle.kts.build.configuration.fasterXmlJackson
import com.gradle.kts.build.configuration.implementation
import com.gradle.kts.kotlin.buildsource.*

plugins {
    id("source-plugin")
    id("com.google.protobuf") version "0.9.4"
}

group = "com.eliasmeireles.kafkalearning"
version = "1.0.0"

sourceSets {
    main {
        proto {
            srcDir("src/main/protobuf")
            srcDir("src/main/protocolbuffers")
            include("**/*.protodevel")
        }
    }
    test {
        proto {
            srcDir("src/test/protocolbuffers")
        }
    }
}

dependencies {
    implementation("org.springframework.kafka:spring-kafka")
    implementation("org.apache.kafka:kafka-clients")

    implementation("com.google.protobuf:protobuf-java:3.16.3")
    implementation("io.grpc:grpc-protobuf:1.15.1")
    implementation("io.grpc:grpc-stub:1.60.0")

    protobuf(files("lib/protos.tar.gz"))

    logstashLogbackEncoderVersion()
    fasterXmlJackson()
    springJettyApi()
    springWebFlux()
    mappstruct()
    jsonLogger()
    passay()

    testProtobuf(files("lib/protos-test.tar.gz"))
    test()
}

protobuf {
    protoc {
        path = "/bin/protoc"
        artifact = "com.google.protobuf:protoc:3.0.0"
    }
}
