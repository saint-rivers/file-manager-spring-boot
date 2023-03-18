package com.saintrivers.resources.service;


import com.saintrivers.common.dto.pagination.PagedResponse;
import com.saintrivers.common.dto.upload.UploadEvent;
import com.saintrivers.common.dto.pagination.PaginationRequest;
import com.saintrivers.resources.web.dto.ResourceResponse;
import com.saintrivers.resources.web.dto.ResourceSearchRequest;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

public interface ResourceService {
    void createFileResource(UploadEvent uploadEvent);

    PagedResponse<Iterable<ResourceResponse>> getResources(@NonNull PaginationRequest paginationRequest, @Nullable ResourceSearchRequest searchRequest);
}
