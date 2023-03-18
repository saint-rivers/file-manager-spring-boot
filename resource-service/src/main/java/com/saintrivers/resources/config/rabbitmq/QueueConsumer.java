package com.saintrivers.resources.config.rabbitmq;

import com.saintrivers.common.dto.upload.UploadEvent;
import com.saintrivers.resources.service.ResourceService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class QueueConsumer {

    private final ResourceService resourceService;

    public QueueConsumer(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    @RabbitListener(queues = {"${queue.name}"})
    public void receive(@Payload UploadEvent fileBody) {
        resourceService.createFileResource(fileBody);
    }
}

