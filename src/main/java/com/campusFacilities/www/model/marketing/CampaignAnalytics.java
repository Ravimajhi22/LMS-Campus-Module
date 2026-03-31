package com.campusFacilities.www.model.marketing;
import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "campaign_analytics")
@Data
public class CampaignAnalytics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Long campaignId;
    
    private Long totalSent;
    
    private Long totalOpened;
    
    private Long totalClicked;
    
    private Long totalConverted;

    private BigDecimal revenueGenerated;
}
