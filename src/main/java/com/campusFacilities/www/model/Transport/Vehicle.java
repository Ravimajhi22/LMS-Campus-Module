package com.campusFacilities.www.model.Transport;
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
@Table(name = "Vehicle")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vehicle {

	
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	    
    @Column(name = "Vechicle_number", nullable = false, unique = true)
    private String VechicleNumber;

	@Enumerated(EnumType.STRING)
    private VehicleType vehicletype;
	
    public enum VehicleType
    {
	    BUS, VAN, CAB
	}
    
    @Column(name = "capacity")
    private Integer capacity;

    @Column(name="occupiedSeats")
    private Integer occupiedSeats;
    
    @Enumerated(EnumType.STRING)
    private VehicleStatus vehicleStatus;
    public enum VehicleStatus
    {
	    ACTIVE,
	    INACTIVE,
	    DEACTIVE
	}
   
    @CreationTimestamp
    @Column(name = "created_date", updatable = false, nullable = false)
    private LocalDateTime createdDate;
    
    @ManyToOne
    @JoinColumn(name = "route_id", nullable = false)
    private RouteWay route;
   

    private Boolean gpsEnabled;

	public void setRoute(RouteWay route) {

		
	}

	public RouteWay getRoute() {
	
		return null;
	}
   
  
}
