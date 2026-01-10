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
     
  
   public enum BusStatus {
	    ACTIVE,
	    INACTIVE,
	    DEACTIVE
	}
  
}
