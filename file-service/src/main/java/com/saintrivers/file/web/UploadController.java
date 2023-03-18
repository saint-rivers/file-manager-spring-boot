package com.saintrivers.file.web;

import com.saintrivers.file.service.UploadService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/files")
public class UploadController {

    private final UploadService uploadService;

    public UploadController(UploadService uploadService) {
        this.uploadService = uploadService;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadFile(@RequestPart("selectedFile") MultipartFile selectedFile) {
        var uploadDetails = uploadService.saveFile(selectedFile);
        return ResponseEntity.ok(uploadDetails);
    }
}

