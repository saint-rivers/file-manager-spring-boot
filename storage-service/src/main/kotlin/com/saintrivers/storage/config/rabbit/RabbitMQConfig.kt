package com.saintrivers.storage.config.rabbit

import org.springframework.amqp.core.Queue
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RabbitMQConfig(@Value("\${queue.name}") val message: String) {

    @Bean
    fun queue(): Queue = Queue(this.message, true)
}