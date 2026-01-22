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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleGPS {
	

		@Id
      	@GeneratedValue(strategy = GenerationType.IDENTITY)
      	private Long id;
	
		 /* ================= VEHICLE REFERENCE ================= */
		   
		@ManyToOne
		@JoinColumn(name = "vehicle_id", nullable = false) // FK → Vehicle.id
		private Vehicle vehicle;


	    /* ================= LOCATION ================= */
	  
	    @Column(nullable = false)
	    private double latitude;

	    @Column(nullable = false)
	    private double longitude;

	    /* ================= SPEED ================= */
	   
	        private double speed; 

	    /* ================= STATUS ================= */
	   
	    @Enumerated(EnumType.STRING)
	    private VehicleGPSStatus status;

	    public enum VehicleGPSStatus {
	        ACTIVE,
	        STOPPED,
	        IDLE,
	        OFFLINE
	    }

	    @CreationTimestamp
	    private LocalDateTime timestamp;
}