package com.campusFacilities.www.model.Library;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "library_settings")
@Data
public class LibrarySettings {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long settingId;

    private Integer maxBooks;

    private Integer issueDurationDays;

    @Column(name = "reservation_duration_days")
    private Integer reservationDurationDays;

    @Column(name = "member_role") // Added for role-based settings (Student/Faculty)
    private String memberRole;

    @JsonManagedReference
    @OneToMany(mappedBy = "librarySettings", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FineSlab> fineSlabs = new ArrayList<>();

    private Boolean isDeleted = false;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // 🔴 alias for service safety
    public Long getId() {
        return this.settingId;
    }

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
