/*
 * package com.campusFacilities.www.controller; import java.util.List; import
 * org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.web.bind.annotation.DeleteMapping; import
 * org.springframework.web.bind.annotation.GetMapping; import
 * org.springframework.web.bind.annotation.PathVariable; import
 * org.springframework.web.bind.annotation.PostMapping; import
 * org.springframework.web.bind.annotation.PutMapping; import
 * org.springframework.web.bind.annotation.RequestBody; import
 * org.springframework.web.bind.annotation.RequestMapping; import
 * org.springframework.web.bind.annotation.RestController; import
 * com.campusFacilities.www.model.marketing.CampaignPerformance; import
 * com.campusFacilities.www.model.marketing.Customer; import
 * com.campusFacilities.www.model.marketing.MarketingContent; import
 * com.campusFacilities.www.service.Imp.MarketingService;
 * 
 * @RestController
 * 
 * @RequestMapping("/marketing") public class MarketingController {
 * 
 * @Autowired private MarketingService marketingService;
 * 
 * // ====================== CAMPAIGN ======================
 * 
 * @PostMapping("/campaign") public CampaignTable createCampaign(@RequestBody
 * CampaignTable campaign) { return marketingService.createCampaign(campaign); }
 * 
 * @GetMapping("/campaign") public List<Campaign> getAllCampaigns() { return
 * marketingService.getAllCampaigns(); }
 * 
 * @GetMapping("/campaign/{id}") public Campaign getCampaignById(@PathVariable
 * Integer id) { return marketingService.getCampaignById(id); }
 * 
 * @PutMapping("/campaign/{id}") public Campaign updateCampaign(
 * 
 * @PathVariable Integer id,
 * 
 * @RequestBody Campaign campaign) { return marketingService.updateCampaign(id,
 * campaign); }
 * 
 * @DeleteMapping("/campaign/{id}") public void deleteCampaign(@PathVariable
 * Integer id) { marketingService.deleteCampaign(id); }
 * 
 * // ====================== MARKETING CONTENT ======================
 * 
 * @PostMapping("/content") public MarketingContent createContent(
 * 
 * @RequestBody MarketingContent content) { return
 * marketingService.createContent(content); }
 * 
 * @GetMapping("/content") public List<MarketingContent> getAllContents() {
 * return marketingService.getAllContents(); }
 * 
 * @GetMapping("/content/{id}") public MarketingContent
 * getContentById(@PathVariable Integer id) { return
 * marketingService.getContentById(id); }
 * 
 * @GetMapping("/content/campaign/{campaignId}") public List<MarketingContent>
 * getContentsByCampaign(
 * 
 * @PathVariable Integer campaignId) { return
 * marketingService.getContentsByCampaign(campaignId); }
 * 
 * @PutMapping("/content/{id}") public MarketingContent updateContent(
 * 
 * @PathVariable Integer id,
 * 
 * @RequestBody MarketingContent content) { return
 * marketingService.updateContent(id, content); }
 * 
 * @DeleteMapping("/content/{id}") public void deleteContent(@PathVariable
 * Integer id) { marketingService.deleteContent(id); }
 * 
 * // ====================== CUSTOMER ======================
 * 
 * @PostMapping("/customer") public Customer registerCustomer(@RequestBody
 * Customer customer) { return marketingService.registerCustomer(customer); }
 * 
 * @GetMapping("/customer") public List<Customer> getAllCustomers() { return
 * marketingService.getAllCustomers(); }
 * 
 * @GetMapping("/customer/{id}") public Customer getCustomerById(@PathVariable
 * Integer id) { return marketingService.getCustomerById(id); }
 * 
 * @PutMapping("/customer/{id}") public Customer updateCustomer(
 * 
 * @PathVariable Integer id,
 * 
 * @RequestBody Customer customer) { return marketingService.updateCustomer(id,
 * customer); }
 * 
 * @DeleteMapping("/customer/{id}") public void deleteCustomer(@PathVariable
 * Integer id) { marketingService.deleteCustomer(id); }
 * 
 * // ====================== CAMPAIGN PERFORMANCE ======================
 * 
 * @PostMapping("/performance") public CampaignPerformance recordPerformance(
 * 
 * @RequestBody CampaignPerformance performance) { return
 * marketingService.recordPerformance(performance); }
 * 
 * @GetMapping("/performance") public List<CampaignPerformance>
 * getAllPerformance() { return marketingService.getAllPerformance(); }
 * 
 * @GetMapping("/performance/{id}") public CampaignPerformance
 * getPerformanceById(
 * 
 * @PathVariable Integer id) { return marketingService.getPerformanceById(id); }
 * 
 * @GetMapping("/performance/campaign/{campaignId}") public
 * List<CampaignPerformance> getPerformanceByCampaign(
 * 
 * @PathVariable Integer campaignId) { return
 * marketingService.getPerformanceByCampaign(campaignId); }
 * 
 * @DeleteMapping("/performance/{id}") public void
 * deletePerformance(@PathVariable Integer id) {
 * marketingService.deletePerformance(id); } }
 */