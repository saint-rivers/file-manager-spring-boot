package com.saintrivers.storage.service

import com.saintrivers.common.dto.upload.UploadEvent
import org.springframework.web.multipart.MultipartFile

interface StorageService {
    fun getExistingFiles(filepath: String): List<String>
    fun saveFile(destination: String, file: MultipartFile): UploadEvent
}