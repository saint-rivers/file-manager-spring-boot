package com.saintrivers.storage.model


data class UploadDto(val systemFilename: String, val displayName: String, val status: FileUploadStatus)