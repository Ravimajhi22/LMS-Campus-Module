package com.campusFacilities.www.repository.marketing;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.campusFacilities.www.model.marketing.MarketingContent;

@Repository
public interface MarketingContentRepository 
        extends JpaRepository<MarketingContent, Integer> {

    // Find by platform
    List<MarketingContent> findByPlatform(String platform);

    // Find by content type
    List<MarketingContent> findByContentType(String contentType);

    // Find by campaign id
    List<MarketingContent> findByCampaign_Id(Long campaignId);
}