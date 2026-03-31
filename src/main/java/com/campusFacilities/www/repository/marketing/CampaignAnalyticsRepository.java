package com.campusFacilities.www.repository.marketing;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.campusFacilities.www.model.marketing.CampaignAnalytics;

@Repository
public interface CampaignAnalyticsRepository 
        extends JpaRepository<CampaignAnalytics, Long> {

    // Find analytics by campaignId
    Optional<CampaignAnalytics> findByCampaignId(Long campaignId);
}