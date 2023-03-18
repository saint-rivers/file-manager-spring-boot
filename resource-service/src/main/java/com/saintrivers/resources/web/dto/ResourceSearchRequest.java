package com.saintrivers.resources.web.dto;

import java.time.LocalDateTime;

public record ResourceSearchRequest(
        String displayName,
        String extension,
        LocalDateTime uploadTime
) {
}
