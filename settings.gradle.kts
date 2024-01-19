rootProject.name = "kafka-learning"

apply("spring-boot-builder-plugin/libs.settings.gradle.kts")
apply("spring-boot-builder-plugin/kotlin-included.build.settings.gradle.kts")

include(":producer")
include(":consumer")
include(":model")
include(":protobuf")

project(":producer").projectDir = file("app/producer")
project(":consumer").projectDir = file("app/consumer")
project(":model").projectDir = file("app/shared/model")
project(":protobuf").projectDir = file("app/shared/protobuf")
