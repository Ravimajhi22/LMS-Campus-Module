package com.campusFacilities.www.model.Transport;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

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
@Table(name = "bus")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bus {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bus_id")
	private Long busId;


    @Column(name = "bus_number", nullable = false, unique = true)
    private String busNumber;

    @Enumerated(EnumType.STRING)
    private BusStatus busStatus;
    
    @Column(name = "driver_name")
    private String driverName;

    @Column(name = "driver_contact")
    private String driverContact;
    
    @Column(name = "license_number")
    private String licenseNumber;

    @Column(name = "license_expiry")
    private LocalDate licenseExpiry;

    @Column(name = "experience_years")
    private Integer experience;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "driver_status", nullable = false)
    private DriverStatus driverStatus;

    @Column(name = "capacity")
    private Integer capacity;

    @Column(name="occupiedSeats")
    private Integer occupiedSeats;
    
    @CreationTimestamp
    @Column(name = "created_date", updatable = false, nullable = false)
    private LocalDateTime createdDate;
    
    // Many Buses → One Route
    
    @ManyToOne
    @JoinColumn(name = "route_id", nullable = false)
    private RouteWay route;
     
   public enum DriverStatus {
    PENDING,
    VERIFIED,
    SUSPENDED,
    REJECTED
}
   public enum BusStatus {
	    ACTIVE,
	    INACTIVE,
	    DEACTIVE
	}

  
}
