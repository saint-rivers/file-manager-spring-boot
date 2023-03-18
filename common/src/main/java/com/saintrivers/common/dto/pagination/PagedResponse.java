package com.saintrivers.common.dto.pagination;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

@Builder
public record PagedResponse<T>(
        String message,
        Integer requestedPage,
        Integer requestedSize,
        Integer totalPages,
        @JsonInclude(JsonInclude.Include.NON_NULL) T payload
) {
}
