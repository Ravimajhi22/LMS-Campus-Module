package com.campusFacilities.www.model.Transport;

import java.time.LocalDate;

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
import lombok.Data;

@Entity
@Data
@Table(name = "drivers")
public class DriverDetails {

	

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long driverId;

	    @Column(nullable = false)
	    private String name;

	    @Column(nullable = false, unique = true)
	    private String contactNumber;

	    @Column(nullable = false, unique = true)
	    private String licenseNumber;

	    @Column(nullable = false)
	    private LocalDate licenseExpiryDate;

	    @Column(nullable = false)
	    private Integer experienceYears;

	    // Assigned Bus (Vehicle)
	    @ManyToOne
	    @JoinColumn(name = "bus_id")
	    private Bus bus;

	    // Assigned Route
	    @ManyToOne
	    @JoinColumn(name = "route_id")
	    private RouteWay route;

	    @Enumerated(EnumType.STRING)
	    @Column(nullable = false)
	    private DriverStatus verificationStatus;
	   
	    public enum DriverStatus {
	        PENDING,
	        VERIFIED,
	        SUSPENDED,
	        REJECTED
	    }
	    
	    @Column(nullable = false)
	    private Boolean active = true;
	    
	    
	}

