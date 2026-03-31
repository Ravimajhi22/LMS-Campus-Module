package com.campusFacilities.www.repository.marketing;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.campusFacilities.www.model.marketing.ClickTracking;

@Repository
public interface ClickTrackingRepository 
        extends JpaRepository<ClickTracking, Long> {

    List<ClickTracking> findByCampaignId(Long campaignId);

    List<ClickTracking> findByUserId(Long userId);

    Long countByCampaignId(Long campaignId);
}