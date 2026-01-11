package com.campusFacilities.www.model.Transport;
import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

	    @Column(nullable = false)
	    private Long busId;

	    @Column(nullable = false)
	    private double latitude;

	    @Column(nullable = false)
	    private double longitude;

	    @Column(nullable = false)
	    private double speed;

	    private String status; 
	    
	    @Column(nullable = false)
	    private LocalDateTime timestamp;
}
