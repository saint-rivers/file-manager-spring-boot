package com.saintrivers.resources.repository;

import com.saintrivers.resources.domain.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResourceRepository extends JpaRepository<Resource, Long> {
}
