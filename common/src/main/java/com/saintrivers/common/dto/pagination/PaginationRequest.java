package com.saintrivers.common.dto.pagination;

import jakarta.validation.constraints.Min;

public record PaginationRequest(
        @Min(1) Integer page, @Min(1) Integer size, SortDirection sortDirection, String property
) {
}
