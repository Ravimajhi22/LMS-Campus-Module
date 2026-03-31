package com.campusFacilities.www.Transport.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleGpsDTO {

	 private Long id;
	    private double latitude;
	    private double longitude;
	    private double speed;
	    private String status;
	    private LocalDateTime timestamp;
	    private Long vehicleId;
}