package com.eliasmeireles.kafkalearning

import com.softwareplace.jsonlogger.log.kLogger
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.ApplicationContext

@SpringBootApplication
class App(
    private val context: ApplicationContext,
) : ApplicationRunner {
    override fun run(args: ApplicationArguments) {
        val port = context.environment.getProperty("server.port", Int::class.java, 7089)
        val path = context.environment.getProperty("server.servlet.context-path", String::class.java, "/")
        kLogger.info("Application started on http://localhost:{}{}", port, path)
    }

}

fun main(args: Array<String>) {
    SpringApplication.run(App::class.java, *args)
}
