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
@Table(name = "marketing_content")
@Data
public class MarketingContent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "content_id")
    private Integer contentId;

    @Column(name = "content_title", length = 150, nullable = false)
    private String contentTitle;

    @Column(name = "content_type", length = 50, nullable = false)
    private String contentType; 
    // Post, Video, Banner, Email

    @Column(name = "platform", length = 50, nullable = false)
    private String platform; 
    // Instagram, Facebook, Email

    @Column(name = "content_url", length = 255)
    private String contentUrl;

    @Column(name = "created_date")
    private LocalDate createdDate;
    
    @ManyToOne
    @JoinColumn(name = "campaign_id", nullable = false)
    private CampaignTable campaign;

}