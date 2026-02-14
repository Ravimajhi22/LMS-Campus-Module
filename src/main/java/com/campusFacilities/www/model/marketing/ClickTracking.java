package com.campusFacilities.www.model.marketing;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "click_tracking")
@Data
public class ClickTracking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long campaignId;
    
    private Long userId;

    private String clickedUrl;
    
    private LocalDateTime clickedAt;
}

