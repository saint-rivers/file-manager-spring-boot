package com.saintrivers.resources.web;

import com.saintrivers.resources.service.ResourceService;
import com.saintrivers.common.dto.pagination.PaginationRequest;
import com.saintrivers.resources.web.dto.ResourceSearchRequest;
import com.saintrivers.common.dto.pagination.SortDirection;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/resources")
public class ResourceController {

    private final ResourceService resourceService;

    public ResourceController(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    @GetMapping
    public ResponseEntity<?> getResources(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "5") Integer size,
            @RequestParam(required = false) String extension,
            @RequestParam(required = false) String property,
            @RequestParam(required = false, name = "sort") SortDirection sortDirection,
            @RequestParam(required = false, name = "filename") String displayName,
            @RequestParam(required = false, name = "date") LocalDate uploadTime
    ) {
        var payload = resourceService.getResources(
                new PaginationRequest(page, size, sortDirection, property),
                new ResourceSearchRequest(displayName, extension, uploadTime == null ? null : LocalDateTime.from(uploadTime))
        );
        return ResponseEntity.ok().body(payload);
    }
}
