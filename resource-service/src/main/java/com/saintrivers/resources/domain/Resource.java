package com.saintrivers.resources.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Table(
        uniqueConstraints = @UniqueConstraint(name = "uk_filename", columnNames = "file_name")
)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Resource {
    @Id
    @GeneratedValue
    @Column(name = "resource_id")
    private Long id;
    @NotBlank
    private String displayName;
    @NotBlank
    @Column(name = "file_name")
    private String fileName;
    @CreatedDate
    private LocalDateTime uploadTime;
    private String extension;
    // list of tags
}
