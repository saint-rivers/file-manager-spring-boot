package com.saintrivers.file.service;

import com.saintrivers.common.dto.upload.UploadEvent;
import org.springframework.web.multipart.MultipartFile;

public interface UploadService {

    UploadEvent saveFile(MultipartFile file);
}
