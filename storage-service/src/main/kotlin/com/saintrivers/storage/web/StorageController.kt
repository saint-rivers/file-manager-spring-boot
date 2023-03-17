package com.saintrivers.storage.web

import com.saintrivers.storage.service.StorageServiceImpl
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/api/v1/storage")
class StorageController(val storageService: StorageServiceImpl) {

    @PostMapping(consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
    fun uploadFile(@RequestPart("selectedFile") file: MultipartFile) {
        storageService.saveFile("uploads", file)
    }

    @GetMapping
    fun listFiles(): List<String> = storageService.getExistingFiles("uploads")
}