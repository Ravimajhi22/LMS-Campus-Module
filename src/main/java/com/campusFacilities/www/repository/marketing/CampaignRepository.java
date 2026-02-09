package com.campusFacilities.www.repository.marketing;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.campusFacilities.www.model.marketing.CampaignTable;
@Repository
public interface CampaignRepository extends JpaRepository<CampaignTable, Integer> {

    List<CampaignTable> findByStatus(String status);

    List<CampaignTable> findByCampaignType(String campaignType);
}

