package com.saintrivers.common.dto.upload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.io.Serializable;
import java.time.LocalDateTime;

@Builder
public record UploadEvent(@NotBlank String displayName, @NotBlank String fileName, @NotBlank String fileExtension,
                          @NotNull LocalDateTime uploadTime) implements Serializable {
}
