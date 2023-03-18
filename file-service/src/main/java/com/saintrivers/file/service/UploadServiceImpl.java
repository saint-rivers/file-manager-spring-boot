package com.saintrivers.file.service;

import com.saintrivers.common.dto.upload.UploadEvent;
import org.springframework.http.MediaType;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.stereotype.Service;
import com.saintrivers.file.config.rabbit.QueueSender;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class UploadServiceImpl implements UploadService{
 private final WebClient storageClient;
    private final QueueSender queueSender;

    @Value("${storage.destination}")
    private String storageDestination;

    public UploadServiceImpl(@Qualifier("storageClient") WebClient storageClient, QueueSender queueSender) {
        this.storageClient = storageClient;
        this.queueSender = queueSender;
    }

    @Override
    public UploadEvent saveFile(MultipartFile selectedFile) {
        MultipartBodyBuilder builder = new MultipartBodyBuilder();
        builder.part("selectedFile", selectedFile.getResource());

        var uploadResponse = storageClient.post()
                .uri("/api/v1/storage/" + storageDestination)
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .body(BodyInserters.fromMultipartData(builder.build()))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(UploadEvent.class);

        return uploadResponse
                .doOnNext(queueSender::send)
                .block();
    }
}
