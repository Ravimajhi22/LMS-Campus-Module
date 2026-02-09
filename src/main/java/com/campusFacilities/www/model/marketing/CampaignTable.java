package com.campusFacilities.www.model.marketing;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "marketing_campaign")
@Data
public class CampaignTable 
{
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "campaign_id")
	    private Integer campaignId;

	    @Column(name = "campaign_name", length = 150, nullable = false)
	    private String campaignName;

	    @Column(name = "campaign_type", length = 50, nullable = false)
	    private String campaignType; 

	    @Column(name = "start_date", nullable = false)
	    private LocalDate startDate;

	    @Column(name = "end_date", nullable = false)
	    private LocalDate endDate;

	    @Column(name = "budget")
	    private Double budget;

	    @Column(name = "status", length = 30)
	    private String status; 

	    @Column(name = "description", length = 500)
	    private String description;

	
		}

