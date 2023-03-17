package com.saintrivers.storage.service

import com.saintrivers.storage.model.UploadDto
import org.springframework.web.multipart.MultipartFile

interface StorageService {
    fun getExistingFiles(filepath: String): List<String>
    fun saveFile(destination: String, file: MultipartFile): UploadDto
}