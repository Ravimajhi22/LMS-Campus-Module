package com.campusFacilities.www.repository.marketing;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.campusFacilities.www.model.marketing.CampaignTable;
import com.campusFacilities.www.model.marketing.CampaignTable.CampaignType;
import com.campusFacilities.www.model.marketing.CampaignTable.ChannelType;
import com.campusFacilities.www.model.marketing.MarketingContent;
@Repository
public interface CampaignRepository 
extends CrudRepository<CampaignTable, Long> {

 

    List<CampaignTable> findByStatus(String status);

    List<CampaignTable> findByCampaignType(String campaignType);
    
    List<MarketingContent> findByCampaign_Id(CampaignType type);

	List<CampaignTable> findByChannel(ChannelType channel);

	Long countByCampaignId(Long campaignId);

}

