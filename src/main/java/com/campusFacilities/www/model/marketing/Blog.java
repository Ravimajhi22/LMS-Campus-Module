package com.campusFacilities.www.model.marketing;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "blogs")
@Data
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ================= BASIC INFO =================

    @Column(nullable = false)
    private String title;

    @Column(unique = true, nullable = false)
    private String slug; 

    @Column(columnDefinition = "TEXT")
    private String content;

    private String shortDescription;

    private String thumbnailUrl;

    // ================= SEO =================

    private String metaTitle;

    private String metaDescription;

    private String metaKeywords;

    // ================= STATUS =================

    @Enumerated(EnumType.STRING)
    private BlogStatus status = BlogStatus.DRAFT;
    public enum BlogStatus {
        DRAFT,
        PUBLISHED,
        ARCHIVED
    }

    // ================= PUBLISHING =================

    private LocalDateTime publishAt;

    private Boolean featured = false;

    // ================= AUDIT =================

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}