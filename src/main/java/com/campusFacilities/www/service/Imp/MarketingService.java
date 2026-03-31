
package com.campusFacilities.www.service.Imp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.campusFacilities.www.model.marketing.Blog;
import com.campusFacilities.www.model.marketing.BlogSetting;
import com.campusFacilities.www.model.marketing.CampaignAnalytics;
import com.campusFacilities.www.model.marketing.CampaignTable;
import com.campusFacilities.www.model.marketing.ClickTracking;
import com.campusFacilities.www.model.marketing.Customer;
import com.campusFacilities.www.model.marketing.DeliveryLog;
import com.campusFacilities.www.model.marketing.MarketingContent;
import com.campusFacilities.www.repository.marketing.BlogRepository;
import com.campusFacilities.www.repository.marketing.BlogSettingRepository;
import com.campusFacilities.www.repository.marketing.CampaignAnalyticsRepository;
import com.campusFacilities.www.repository.marketing.CampaignRepository;
import com.campusFacilities.www.repository.marketing.ClickTrackingRepository;
import com.campusFacilities.www.repository.marketing.CustomerRepository;
import com.campusFacilities.www.repository.marketing.DeliveryLogRepository;
import com.campusFacilities.www.repository.marketing.MarketingContentRepository;

@Service
public class MarketingService {

    @Autowired
    private CampaignRepository repository;

    @Autowired
    private CampaignAnalyticsRepository CampionAnalyticsrepository;

    @Autowired
    private ClickTrackingRepository ClickTrackingrepository;

    @Autowired
    private CustomerRepository customerrepository;

    @Autowired
    private BlogRepository blogrepository;

    @Autowired
    private BlogSettingRepository blogSettingsrepository;

    @Autowired
    private MarketingContentRepository marketingContentrepository;

    @Autowired
    private DeliveryLogRepository delivarylogrepository;

    // ====================== Campaign ======================

    public CampaignTable createCampaign(CampaignTable campaign) {
        return repository.save(campaign);
    }

    public CampaignTable updateCampaign(Long id, CampaignTable campaign) {
        CampaignTable existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Campaign not found"));

        existing.setCampaignName(campaign.getCampaignName());
        existing.setChannel(campaign.getChannel());
        existing.setType(campaign.getType());
        existing.setCompanyName(campaign.getCompanyName());
        existing.setAddress(campaign.getAddress());
        existing.setCity(campaign.getCity());
        existing.setState(campaign.getState());
        existing.setZipCode(campaign.getZipCode());
        existing.setCountry(campaign.getCountry());

        return repository.save(existing);
    }

    public List<CampaignTable> getAllCampaigns() {
        return (List<CampaignTable>) repository.findAll();
    }

    public CampaignTable getCampaignById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Campaign not found"));
    }

    public List<CampaignTable> getByChannel(CampaignTable.ChannelType channel) {
        return repository.findByChannel(channel);
    }

    public void deleteCampaign(Long id) {
        repository.deleteById(id);
    }

    // ====================== Campaign Analytics ======================

    public CampaignAnalytics saveAnalytics(CampaignAnalytics analytics) {
        return CampionAnalyticsrepository.save(analytics);
    }

    public List<CampaignAnalytics> getAllAnalytics() {
        return CampionAnalyticsrepository.findAll();
    }

    public CampaignAnalytics getAnalyticsById(Long id) {
        return CampionAnalyticsrepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Analytics not found"));
    }

    public CampaignAnalytics getAnalyticsByCampaignId(Long campaignId) {
        return CampionAnalyticsrepository.findByCampaignId(campaignId)
                .orElseThrow(() -> new RuntimeException("Campaign analytics not found"));
    }

    public void deleteAnalytics(Long id) {
        CampionAnalyticsrepository.deleteById(id);
    }

    // ====================== Click Tracking ======================

    public ClickTracking saveClick(ClickTracking clickTracking) {
        clickTracking.setClickedAt(LocalDateTime.now());
        return ClickTrackingrepository.save(clickTracking);
    }

    public List<ClickTracking> getAllClicks() {
        return ClickTrackingrepository.findAll();
    }

    public ClickTracking getClickById(Long id) {
        return ClickTrackingrepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Click record not found"));
    }

    public List<ClickTracking> getClicksByCampaignId(Long campaignId) {
        return ClickTrackingrepository.findByCampaignId(campaignId);
    }

    public List<ClickTracking> getClicksByUserId(Long userId) {
        return ClickTrackingrepository.findByUserId(userId);
    }

    public void deleteClick(Long id) {
        ClickTrackingrepository.deleteById(id);
    }

    // ====================== Customer ======================

    public Customer createCustomer(Customer customer) {
        if (customerrepository.existsByEmail(customer.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        customer.setCreatedDate(LocalDate.now());
        return customerrepository.save(customer);
    }

    public List<Customer> getAllCustomers() {
        return customerrepository.findAll();
    }

    public Customer getCustomerById(Integer id) {
        return customerrepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
    }

    public Customer getByEmail(String email) {
        return customerrepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
    }

    public void deleteCustomer(Integer id) {
        customerrepository.deleteById(id);
    }

    // ====================== Blog ======================

    public Blog createBlog(Blog blog) {
        if (blogrepository.existsBySlug(blog.getSlug())) {
            throw new RuntimeException("Slug already exists");
        }
        return blogrepository.save(blog);
    }

    public List<Blog> getAllBlogs() {
        return blogrepository.findAll();
    }

    public Blog getBlogById(Long id) {
        return blogrepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Blog not found"));
    }

    public void deleteBlog(Long id) {
        blogrepository.deleteById(id);
    }

    // ====================== Blog Settings ======================

    public BlogSetting saveSetting(BlogSetting setting) {
        return blogSettingsrepository.save(setting);
    }

    public List<BlogSetting> getAllSettings() {
        return blogSettingsrepository.findAll();
    }

    public BlogSetting getBlogSettingById(Long id) {
        return blogSettingsrepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Blog setting not found"));
    }

    public void deleteSetting(Long id) {
        blogSettingsrepository.deleteById(id);
    }

    // ====================== Delivery Log ======================

    public DeliveryLog saveLog(DeliveryLog log) {
        log.setSentAt(LocalDateTime.now());
        return delivarylogrepository.save(log);
    }

    public List<DeliveryLog> getAllLogs() {
        return delivarylogrepository.findAll();
    }

    public DeliveryLog getDeliveryLogById(Long id) {
        return delivarylogrepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Delivery log not found"));
    }

    public List<DeliveryLog> getDeliveryLogsByCampaignId(Long campaignId) {
        return delivarylogrepository.findByCampaignId(campaignId);
    }

    public void deleteLog(Long id) {
        delivarylogrepository.deleteById(id);
    }

    // ====================== Marketing Content ======================

    public MarketingContent createContent(MarketingContent content) {
        content.setCreatedDate(LocalDate.now());
        return marketingContentrepository.save(content);
    }

    public List<MarketingContent> getAllContent() {
        return marketingContentrepository.findAll();
    }

    public MarketingContent getContentById(Integer id) {
        return marketingContentrepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Content not found"));
    }

    public void deleteContent(Integer id) {
        marketingContentrepository.deleteById(id);
    }
}




