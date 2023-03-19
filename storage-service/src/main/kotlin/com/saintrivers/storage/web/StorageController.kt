package com.saintrivers.storage.web

import com.saintrivers.common.dto.upload.UploadEvent
import com.saintrivers.storage.service.StorageService
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/api/v1/storage")
class StorageController(val storageService: StorageService) {

    @PostMapping(consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
    fun uploadFile(@RequestPart("selectedFile") file: MultipartFile) {
        storageService.saveFile("/opt/resources", file)
    }

    @PostMapping(consumes = [MediaType.MULTIPART_FORM_DATA_VALUE], value = ["/{destinationPath}"])
    fun uploadFileWithDirectory(
        @RequestPart("selectedFile") file: MultipartFile,
        @PathVariable destinationPath: String
    ): ResponseEntity<UploadEvent> {
        val payload = storageService.saveFile(destinationPath, file)
        return ResponseEntity.ok().body(payload)
    }

    @GetMapping
    fun listFiles(): List<String> = storageService.getExistingFiles("uploads")
}