package com.campusFacilities.www.repository.marketing;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.campusFacilities.www.model.marketing.DeliveryLog;

@Repository
public interface DeliveryLogRepository 
        extends JpaRepository<DeliveryLog, Long> {

    // Find by campaign
    List<DeliveryLog> findByCampaignId(Long campaignId);

    // Find by user
    List<DeliveryLog> findByUserId(Long userId);

    // Count delivered by campaign
    Long countByCampaignIdAndDeliveredTrue(Long campaignId);

    // Count opened by campaign
    Long countByCampaignIdAndOpenedTrue(Long campaignId);

    // Count clicked by campaign
    Long countByCampaignIdAndClickedTrue(Long campaignId);
}