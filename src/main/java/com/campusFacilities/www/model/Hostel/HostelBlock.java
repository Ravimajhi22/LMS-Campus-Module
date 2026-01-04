package com.campusFacilities.www.model.Hostel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;

    @Entity
	@Table(name = "hostel_blocks", uniqueConstraints = {@UniqueConstraint(columnNames = {"hostel_id","block_name"})})
    @Data
	public class HostelBlock {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long blockId;

	    @ManyToOne
	    @JoinColumn(name = "hostel_id", nullable = false)
	    private Hostel hostel;

	    @Column(nullable = false)
	    private String blockName;

	    private Integer totalFloors = 1;

	    @Enumerated(EnumType.STRING)
	    private Status status = Status.ACTIVE;
	    
	    public enum Status 
	    {
	        ACTIVE,
	        INACTIVE
	    }
	}

