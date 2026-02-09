package com.campusFacilities.www.repository.marketing;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.campusFacilities.www.model.marketing.CampaignPerformance;

@Repository
public interface CampaignPerformanceRepository 
        extends JpaRepository<CampaignPerformance, Integer> {

    List<CampaignPerformance> findByCampaign_CampaignId(Integer campaignId);
   }