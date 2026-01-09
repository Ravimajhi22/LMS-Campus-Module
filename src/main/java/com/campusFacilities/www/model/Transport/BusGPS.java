package com.campusFacilities.www.model.Transport;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class BusGPS {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long busId;
    private double latitude;
    private double longitude;
    private LocalDateTime timestamp;
    private double speed;
    private double direction;
}
