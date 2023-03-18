package com.saintrivers.resources.service;

import com.saintrivers.common.dto.pagination.PagedResponse;
import com.saintrivers.common.dto.upload.UploadEvent;
import com.saintrivers.common.mapper.PaginationMapper;
import com.saintrivers.resources.domain.Resource;
import com.saintrivers.resources.mapper.ResourceMapper;
import com.saintrivers.resources.repository.ResourceRepository;
import com.saintrivers.common.dto.pagination.PaginationRequest;
import com.saintrivers.resources.web.dto.ResourceResponse;
import com.saintrivers.resources.web.dto.ResourceSearchRequest;
import org.springframework.data.domain.*;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Service
public class ResourceServiceImpl implements ResourceService {
    private final ResourceRepository resourceRepository;
    private final ResourceMapper resourceMapper;
    private final PaginationMapper paginationMapper;

    public ResourceServiceImpl(ResourceRepository resourceRepository,
                               ResourceMapper resourceMapper,
                               PaginationMapper paginationMapper) {
        this.resourceRepository = resourceRepository;
        this.resourceMapper = resourceMapper;
        this.paginationMapper = paginationMapper;
    }

    @Override
    public void createFileResource(UploadEvent uploadEvent) {
        Resource resource = Resource.builder()
                .displayName(uploadEvent.displayName())
                .extension(uploadEvent.fileExtension())
                .fileName(uploadEvent.fileName())
                .uploadTime(uploadEvent.uploadTime())
                .build();
        resourceRepository.save(resource);
    }

    @Override
    public PagedResponse<Iterable<ResourceResponse>> getResources(
            @NonNull PaginationRequest paginationRequest,
            ResourceSearchRequest searchRequest) {

        Pageable pageable = this.generatePageable(paginationRequest);
        Example<Resource> example = this.generateExample(searchRequest);

        var result = resourceRepository.findAll(example, pageable);
        var payload = result.map(resourceMapper::toResourceResponse);
        return paginationMapper.toPagedResponse(paginationRequest.page(), payload, "resources fetched");
    }

    private Example<Resource> generateExample(ResourceSearchRequest searchRequest) {
        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                .withIgnoreCase("displayName", "extension", "uploadTime")
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        return Example.of(
                Resource.builder()
                        .displayName(searchRequest.displayName())
                        .extension(searchRequest.extension())
                        .uploadTime(searchRequest.uploadTime())
                        .build(),
                exampleMatcher
        );
    }

    private Pageable generatePageable(@NonNull PaginationRequest paginationRequest) {
        if (paginationRequest.sortDirection() == null || paginationRequest.property() == null)
            return PageRequest.of(
                    paginationRequest.page() - 1,
                    paginationRequest.size()
            );

        else {
            Sort.Direction dir = switch (paginationRequest.sortDirection()) {
                case ASC -> Sort.Direction.ASC;
                case DESC -> Sort.Direction.DESC;
            };
            return PageRequest.of(
                    paginationRequest.page() - 1,
                    paginationRequest.size(),
                    Sort.by(dir, paginationRequest.property())
            );
        }
    }
}
