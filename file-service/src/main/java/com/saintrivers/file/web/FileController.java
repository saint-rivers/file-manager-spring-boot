package com.saintrivers.file.web;

import com.saintrivers.common.dto.UploadEvent;
import com.saintrivers.file.config.rabbit.QueueSender;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/files")
public class FileController {

    private final WebClient storageClient;
    private final QueueSender queueSender;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadFile(@RequestPart("selectedFile") MultipartFile selectedFile) {
        MultipartBodyBuilder builder = new MultipartBodyBuilder();
        builder.part("selectedFile", selectedFile.getResource());

        var uploadResponse = storageClient.post()
                .uri("/api/v1/storage/uploads")
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .body(BodyInserters.fromMultipartData(builder.build()))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(UploadEvent.class).log();

        var uploadDetails = uploadResponse
                .doOnNext(queueSender::send)
                .block();
        return ResponseEntity.ok(uploadDetails);
    }
}

