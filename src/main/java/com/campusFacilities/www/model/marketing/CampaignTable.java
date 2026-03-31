
 package com.campusFacilities.www.model.marketing; import
  jakarta.persistence.Column;
import jakarta.persistence.Entity;
import
  jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import
  jakarta.persistence.GeneratedValue;
import
  jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import
  jakarta.persistence.Table;
import lombok.Data;

  @Entity
  @Table(name = "marketing_campaign") 
  @Data 
  public class CampaignTable {
  
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @Column(nullable = false)
	    private String campaignName;

	 
	    @Enumerated(EnumType.STRING)
	    @Column(nullable = false)
	    private ChannelType channel;
	    public enum ChannelType {
	        EMAIL,
	        WHATSAPP,
	        PUSH_NOTIFICATION,
	        SMS,
	        TELEGRAM,
	        IN_APP
	    }
	    
	    @Enumerated(EnumType.STRING)
	    @Column(nullable = false)
	    private CampaignType type;
	    public enum CampaignType {
	        BROADCAST,
	        TRIGGER_BASED
	    }

	    @Column(nullable = false)
	    private String companyName;

	    @Column(nullable = false)
	    private String address;

	    @Column(nullable = false)
	    private String city;

	    @Column(nullable = false)
	    private String state;

	    @Column(nullable = false)
	    private String zipCode;

	    @Column(nullable = false)
	    private String country;
	}