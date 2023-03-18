package com.saintrivers.common.mapper;

import com.saintrivers.common.dto.pagination.PagedResponse;
import org.springframework.data.domain.Page;

import java.util.stream.Collectors;

public class PaginationMapper {

    public <T> PagedResponse<Iterable<T>> toPagedResponse(Integer page, Page<T> payload, String message) {
        return PagedResponse.<Iterable<T>>builder()
                .requestedSize(payload.getSize())
                .requestedPage(page)
                .totalPages(payload.getTotalPages())
                .payload(payload.get().collect(Collectors.toSet()))
                .message(message)
                .build();
    }
}
