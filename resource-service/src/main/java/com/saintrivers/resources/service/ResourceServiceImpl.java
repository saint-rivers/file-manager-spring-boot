package com.saintrivers.resources.service;

import com.saintrivers.common.dto.UploadEvent;
import com.saintrivers.resources.domain.Resource;
import com.saintrivers.resources.repository.ResourceRepository;
import org.springframework.stereotype.Service;

@Service
public class ResourceServiceImpl implements ResourceService {
    private final ResourceRepository resourceRepository;

    public ResourceServiceImpl(ResourceRepository resourceRepository) {
        this.resourceRepository = resourceRepository;
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
}
