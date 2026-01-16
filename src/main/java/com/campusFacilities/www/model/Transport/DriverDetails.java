package com.campusFacilities.www.model.Transport;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "drivers")
public class DriverDetails 
{
	
	    @Column(nullable = false)
	    private String name;
        
	    @Id
	    @Column(nullable = false, unique = true)
	    private String contactNumber;
	    
	    @Column(nullable = false, unique = true)
	    private String licenseNumber;

	    @Column(nullable = false)
	    private LocalDate licenseExpiryDate;
	    
	    @Enumerated(EnumType.STRING)
	    private Role role;
	    public enum Role
	    {
	    	DRIVER, CONDUCTOR ,HELPER
	    }
	    
	    @Enumerated(EnumType.STRING)
	    private ExperienceCategory experienceCategory;
	    public enum ExperienceCategory
	    {
	    	SCHOOLBUS, HEAVYVEHICLE, LIGHTVEHICLE ,BOTHSCHOOLBUSANDHEAVYVEHICLE
	    }
	    
	    private Boolean backgroundVerified;
	    
	    @Enumerated(EnumType.STRING)
	    private ShiftType shift;
	    public enum ShiftType {
             MORNING, EVENING ,BOTH }
	    
	    @Column(nullable = false)
	    private Integer experienceYears;
	    
	    @Enumerated(EnumType.STRING)
	    private LicenseValidityStatus licenseValidityStatus;

	    public enum LicenseValidityStatus {
	        VALID,
	        EXPIRED,
	        EXPIRING_SOON
	    }
	    // Assigned Bus (Vehicle)
	    
	    @ManyToOne
	    @JoinColumn(name = "Vehicle")
	    private Vehicle vehicle;

	    // Assigned Route
	    @ManyToOne
	    @JoinColumn(name = "route_code")
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

