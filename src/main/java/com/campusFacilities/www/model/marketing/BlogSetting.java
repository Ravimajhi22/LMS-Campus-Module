package com.campusFacilities.www.model.marketing;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

public class BlogSetting {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ============ BLOG HOME SCRIPTS ============

    @Column(columnDefinition = "TEXT")
    private String homeHeadScript;

    @Column(columnDefinition = "TEXT")
    private String homeBodyScript;

    // ============ INDIVIDUAL BLOG DEFAULT SCRIPTS ============

    @Column(columnDefinition = "TEXT")
    private String blogHeadScript;

    @Column(columnDefinition = "TEXT")
    private String blogBodyScript;

    // ============ AUDIT ============

    @PrePersist
    public void onCreate() {
        LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate() {
        LocalDateTime.now();
    }
}
