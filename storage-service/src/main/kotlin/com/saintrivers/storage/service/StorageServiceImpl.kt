package com.saintrivers.storage.service

import com.saintrivers.common.dto.upload.UploadEvent
import com.saintrivers.storage.exception.BadUploadException
import mu.KotlinLogging
import org.apache.tomcat.util.http.fileupload.FileUploadException
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.io.FileNotFoundException
import java.lang.IllegalStateException
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.time.LocalDateTime
import java.util.*

@Service
class StorageServiceImpl : StorageService {

    private val log = KotlinLogging.logger {}

    override fun saveFile(destination: String, file: MultipartFile): UploadEvent {
        if (file.originalFilename == null) throw BadUploadException()

        val uploadsFolderPath: Path = Paths.get(destination)
        val formattedName = file.originalFilename!!.generateUniqueName()
        val uploadedTargetPath = uploadsFolderPath.resolve(formattedName)

        // attempt to save
        kotlin.runCatching {
            Files.copy(file.inputStream, uploadedTargetPath)
            return UploadEvent(
                file.originalFilename!!,
                formattedName,
                file.getExtension(),
                LocalDateTime.now()
            )
        }.onFailure {
            log.error { "Error saving file: ${it.message}" }
        }
        throw FileUploadException("error saving file. please try again.")
    }

    override fun getExistingFiles(filepath: String): List<String> {

        val folders: Array<File>? = File(filepath).listFiles { file -> file.isFile }

        if (folders != null) {
            return folders.map {
                "http://localhost:9092/uploads/${it.name}"
            }
        }
        throw FileNotFoundException()
    }
}

private fun MultipartFile.getExtension(): String {
    val filename = this.originalFilename ?: throw IllegalStateException("file name not found")
    return filename.substring(filename.lastIndexOf(".") + 1)
}

private fun String.getExtension(): String {
    val filename = this
    return filename.substring(filename.lastIndexOf(".") + 1)
}


private fun String.normalizeFilename(): String {
    var newName = this.trim().lowercase()
    newName = newName.replace(" ", "-", true)
    return newName
}

private fun String.generateUuidForFilename() = "${UUID.randomUUID()}.${this.getExtension()}"

private fun String.generateUniqueName() = this.normalizeFilename().generateUuidForFilename()
