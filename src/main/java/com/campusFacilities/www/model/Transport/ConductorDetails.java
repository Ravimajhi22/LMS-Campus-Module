package com.campusFacilities.www.model.Transport;

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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "conductors")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConductorDetails {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long conductorId;

	    @Column(nullable = false)
	    private String name;

	    @Column(nullable = false, unique = true)
	    private String contactNumber;

	    @Column(nullable = false)
	    private Integer experienceYears;

	    // Assigned Bus
	    @ManyToOne
	    @JoinColumn(name = "")
	    private Vehicle vehicle;

	    // Assigned Route
	    @ManyToOne
	    @JoinColumn(name = "route_id")
	    private RouteWay route;

	    @Enumerated(EnumType.STRING)
	    @Column(nullable = false)
	    private ConductorVerificationStatus verificationStatus;
	    
	    public enum ConductorVerificationStatus {
	        PENDING,
	        VERIFIED,
	        SUSPENDED,
	        REJECTED
	    }

	    @Column(nullable = false)
	    private Boolean active = true;
	}

