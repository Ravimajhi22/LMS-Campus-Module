package com.campusFacilities.www.service.Imp;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.campusFacilities.www.model.marketing.CampaignPerformance;
import com.campusFacilities.www.model.marketing.CampaignTable;
import com.campusFacilities.www.model.marketing.Customer;
import com.campusFacilities.www.model.marketing.MarketingContent;
import com.campusFacilities.www.repository.marketing.CampaignPerformanceRepository;
import com.campusFacilities.www.repository.marketing.CampaignRepository;
import com.campusFacilities.www.repository.marketing.CustomerRepository;
import jakarta.transaction.Transactional;

@Service
public class MarketingService {
	
	 @Autowired
	    private CustomerRepository customerRepo;

	    @Autowired
	    private CampaignRepository campaignRepo;

	    @Autowired
	    private MarketingContent markContRepo;

	    @Autowired
	    private CampaignPerformanceRepository campPerRepo;

	    // ====================== CAMPAIGN ======================

	    public CampaignTable createCampaign(CampaignTable campaign) {

	        if (campaign.getCampaignName() == null) {
	            throw new ResponseStatusException(
	                    HttpStatus.BAD_REQUEST,
	                    "CAMPAIGN_NAME_REQUIRED"
	            );
	        }

	        campaign.setStatus("Active");
	        return campaignRepo.save(campaign);
	    }

	    public List<CampaignTable> getAllCampaigns() {
	        return campaignRepo.findAll();
	    }

	    public CampaignTable getCampaignById(Integer id) {
	        return campaignRepo.findById(id)
	                .orElseThrow(() ->
	                        new ResponseStatusException(
	                                HttpStatus.NOT_FOUND,
	                                "CAMPAIGN_NOT_FOUND"
	                        ));
	    }

	    public CampaignTable updateCampaign(Integer id, CampaignTable request) {

	        CampaignTable existing = getCampaignById(id);

	        if (request.getCampaignName() != null)
	            existing.setCampaignName(request.getCampaignName());

	        if (request.getCampaignType() != null)
	            existing.setCampaignType(request.getCampaignType());

	        if (request.getStartDate() != null)
	            existing.setStartDate(request.getStartDate());

	        if (request.getEndDate() != null)
	            existing.setEndDate(request.getEndDate());

	        if (request.getBudget() != null)
	            existing.setBudget(request.getBudget());

	        if (request.getStatus() != null)
	            existing.setStatus(request.getStatus());

	        if (request.getDescription() != null)
	            existing.setDescription(request.getDescription());

	        return campaignRepo.save(existing);
	    }

	    public void deleteCampaign(Integer id) {
	        campaignRepo.deleteById(id);
	    }

	    // ====================== MARKETING CONTENT ======================

	    public MarketingContent createContent(MarketingContent content) {

	        if (content.getCampaign() == null) {
	            throw new ResponseStatusException(
	                    HttpStatus.BAD_REQUEST,
	                    "CAMPAIGN_REQUIRED"
	            );
	        }

	        content.setCreatedDate(LocalDate.now());
	        return markContRepo.save(content);
	    }

	    public List<MarketingContent> getAllContents() {
	        return markContRepo.findAll();
	    }

	    public CampaignTable getContentById(Integer id) {
	        return markContRepo.findById(id)
	                .orElseThrow(() ->
	                        new ResponseStatusException(
	                                HttpStatus.NOT_FOUND,
	                                "CONTENT_NOT_FOUND"
	                        ));
	    }

	    public List<MarketingContent> getContentsByCampaign(Integer campaignId) {
	        return markContRepo.findByCampaign_CampaignId(campaignId);
	    }

	    public MarketingContent updateContent(Integer id, MarketingContent request) {

	        CampaignTable existing = getContentById(id);

	        if (request.getContentTitle() != null)
	            existing.setContentTitle(request.getContentTitle());

	        if (request.getContentType() != null)
	            existing.setContentTitle(request.getContentType());

	        if (request.getPlatform() != null)
	            existing.setPlatform(request.getPlatform());

	        if (request.getContentUrl() != null)
	            existing.setContentTitle(request.getContentUrl());

	        return markContRepo.save(existing);
	    }

	    public void deleteContent(Integer id) {
	        markContRepo.deleteById(id);
	    }

	    // ====================== CUSTOMER ======================

	    @Transactional
	    public Customer registerCustomer(Customer customer) {

	        if (customerRepo.existsByEmail(customer.getEmail())) {
	            throw new ResponseStatusException(
	                    HttpStatus.CONFLICT,
	                    "EMAIL_ALREADY_EXISTS"
	            );
	        }

	        customer.setCreatedDate(LocalDate.now());
	        return customerRepo.save(customer);
	    }

	    public List<Customer> getAllCustomers() {
	        return customerRepo.findAll();
	    }

	    public Customer getCustomerById(Integer id) {
	        return customerRepo.findById(id)
	                .orElseThrow(() ->
	                        new ResponseStatusException(
	                                HttpStatus.NOT_FOUND,
	                                "CUSTOMER_NOT_FOUND"
	                        ));
	    }

	    public Customer updateCustomer(Integer id, Customer request) {

	        Customer existing = getCustomerById(id);

	        if (request.getName() != null)
	            existing.setName(request.getName());

	        if (request.getPhone() != null)
	            existing.setPhone(request.getPhone());

	        if (request.getLocation() != null)
	            existing.setLocation(request.getLocation());

	        return customerRepo.save(existing);
	    }

	    public void deleteCustomer(Integer id) {
	        customerRepo.deleteById(id);
	    }

	    // ====================== CAMPAIGN PERFORMANCE ======================

	    @Transactional
	    public CampaignPerformance recordPerformance(CampaignPerformance performance) {

	        if (performance.getCampaign() == null) {
	            throw new ResponseStatusException(
	                    HttpStatus.BAD_REQUEST,
	                    "CAMPAIGN_REQUIRED"
	            );
	        }

	        performance.setRecordedDate(LocalDate.now());

	        if (performance.getClicks() != null && performance.getClicks() > 0) {
	            double rate =
	                    (double) performance.getConversions()
	                            / performance.getClicks() * 100;
	            performance.setConversionRate(rate);
	        } else {
	            performance.setConversionRate(0.0);
	        }

	        return campPerRepo.save(performance);
	    }

	    public List<CampaignPerformance> getAllPerformance() {
	        return campPerRepo.findAll();
	    }

	    public CampaignPerformance getPerformanceById(Integer id) {
	        return campPerRepo.findById(id)
	                .orElseThrow(() ->
	                        new ResponseStatusException(
	                                HttpStatus.NOT_FOUND,
	                                "PERFORMANCE_NOT_FOUND"
	                        ));
	    }

	    public List<CampaignPerformance> getPerformanceByCampaign(Integer campaignId) {
	        return campPerRepo.findByCampaign_CampaignId(campaignId);
	    }

	    public void deletePerformance(Integer id) {
	        campPerRepo.deleteById(id);
	    }
	}