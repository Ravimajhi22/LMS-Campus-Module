package com.campusFacilities.www.model.marketing;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "promo_codes")
@Data
public class PromoCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ================= BASIC =================

    @Column(unique = true, nullable = false)
    private String code;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProductType productType;
    public enum ProductType {
        COURSE_PACKAGE,
        TELEGRAM_COMMUNITY,
        AVATAR
    }

    private Long productId;

    // ================= DISCOUNT =================

    @Enumerated(EnumType.STRING)
    private DiscountType discountType;
    public enum DiscountType {

        PERCENTAGE,
        FIXED_AMOUNT
    }

    private BigDecimal discountValue;

    private BigDecimal maximumDiscountValue;

    private BigDecimal minimumProductValue;

    private Integer maxUseCount;

    private Integer usedCount = 0;

    private Boolean eligibleForEnrolledOnly = false;

    @ElementCollection
    @CollectionTable(name = "promo_eligible_courses",
   joinColumns = @JoinColumn(name = "promo_id"))
    @Column(name = "course_id")
    private List<Long> eligibleCourseIds;

    // ================= VALIDITY =================

    private LocalDate validTill;

    // ================= STATUS =================

    private Boolean active = true;

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
