package com.saintrivers.resources.mapper;

import com.saintrivers.resources.domain.Resource;
import com.saintrivers.resources.web.dto.ResourceResponse;
import org.springframework.stereotype.Component;

@Component
public class ResourceMapper {

    public ResourceResponse toResourceResponse(Resource resource){
        return ResourceResponse.builder()
                .displayName(resource.getDisplayName())
                .uploadTime(resource.getUploadTime())
                .extension(resource.getExtension())
                .fileName(resource.getFileName())
                .build();
    }
}
