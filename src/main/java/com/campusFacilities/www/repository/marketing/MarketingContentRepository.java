package com.campusFacilities.www.repository.marketing;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.campusFacilities.www.model.marketing.MarketingContent;

@Repository
public interface MarketingContentRepository 
        extends JpaRepository<MarketingContent, Integer> {

    List<MarketingContent> findByPlatform(String platform);

    List<MarketingContent> findByCampaign_CampaignId(Integer campaignId);
}