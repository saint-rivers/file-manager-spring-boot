package com.saintrivers.storage

import org.springframework.amqp.rabbit.annotation.EnableRabbit
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableRabbit
class StorageServiceApplication

fun main(args: Array<String>) {
    runApplication<StorageServiceApplication>(*args)
}
