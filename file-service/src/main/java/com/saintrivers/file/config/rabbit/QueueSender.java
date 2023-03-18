package com.saintrivers.file.config.rabbit;

import com.saintrivers.common.dto.upload.UploadEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class QueueSender {

    private final RabbitTemplate rabbitTemplate;
    private final Queue queue;

    public void send(UploadEvent uploadEvent){
        rabbitTemplate.convertAndSend(queue.getName(), uploadEvent);
    }

}
