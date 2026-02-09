package com.campusFacilities.www.model.marketing;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "campaign_performance")
@Data
public class CampaignPerformance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "performance_id")
    private Integer performanceId;


    @ManyToOne
    @JoinColumn(name = "campaign_id", nullable = false)
    private CampaignTable campaign;

    @Column(name = "impressions")
    private Long impressions;

    @Column(name = "clicks")
    private Long clicks;

    @Column(name = "conversions")
    private Long conversions;

    @Column(name = "cost_per_click")
    private Double costPerClick;

    @Column(name = "conversion_rate")
    private Double conversionRate;

    @Column(name = "recorded_date")
    private LocalDate recordedDate;

}	