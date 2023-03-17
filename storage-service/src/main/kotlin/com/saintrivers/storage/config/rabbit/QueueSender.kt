package com.saintrivers.storage.config.rabbit

import com.saintrivers.common.dto.UploadEvent
import org.springframework.amqp.core.Queue
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.stereotype.Component

@Component
class QueueSender(val rabbitTemplate: RabbitTemplate, val queue: Queue) {

    fun send(uploadEvent: UploadEvent) {
        rabbitTemplate.convertAndSend(this.queue.name, uploadEvent)
    }
}