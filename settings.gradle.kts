rootProject.name = "kafka-learning"

apply("spring-boot-builder-plugin/libs.settings.gradle.kts")
apply("spring-boot-builder-plugin/kotlin-included.build.settings.gradle.kts")

include(":app")
