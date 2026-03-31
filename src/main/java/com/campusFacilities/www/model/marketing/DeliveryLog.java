package com.campusFacilities.www.model.marketing;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "delivery_logs")
@Data
public class DeliveryLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long campaignId;
    
    private Long userId;

    private Boolean delivered;
    
    private Boolean opened;
    
    private Boolean clicked;

    private LocalDateTime sentAt;
}
