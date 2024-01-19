import com.google.protobuf.gradle.proto
import com.gradle.kts.build.configuration.implementation

plugins {
    id("source-plugin")
    id("submodule-source-plugin")
    id("com.google.protobuf") version "0.9.4"
}

group = "com.eliasmeireles.kafkalearning.protobuf"
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
repositories {
    maven("https://packages.confluent.io/maven/")
}

dependencies {
    implementation("com.google.protobuf:protobuf-java:3.16.3")
    implementation("io.grpc:grpc-protobuf:1.15.1")
    implementation("io.grpc:grpc-stub:1.60.0")

    protobuf(files("lib/protos.tar.gz"))

    testProtobuf(files("lib/protos-test.tar.gz"))
}

protobuf {
    protoc {
        path = "/bin/protoc"
        artifact = "com.google.protobuf:protoc:3.0.0"
    }
}

tasks.named("test").configure {
    enabled = false
}
