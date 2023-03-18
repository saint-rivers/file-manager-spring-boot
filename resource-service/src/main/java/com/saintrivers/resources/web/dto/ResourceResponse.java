package com.saintrivers.resources.web.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ResourceResponse(
        String displayName,
        String extension,
        String fileName,
        LocalDateTime uploadTime
) {
}
