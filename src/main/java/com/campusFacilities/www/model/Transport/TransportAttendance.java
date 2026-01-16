package com.campusFacilities.www.model.Transport;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
	@Table(name = "transport_attendance")
	@Data
	public class TransportAttendance {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private Long studentId;
	    
	    private Long pickupStopId;
	    
	    private Long dropStopId;

	    private Long VechileId;

	    private Long routeId;


	    private LocalDate attendanceDate;
	    
	    @Enumerated(EnumType.STRING)
	    private AttendanceStatus status;
	    
	    public enum AttendanceStatus {
	        PRESENT,
	        ABSENT,
	        SKIPPED
	    }
	    @Enumerated(EnumType.STRING)
	    private MarkedBy markedBy;
	    
	    public enum MarkedBy {
	        MANUAL,
	        QR,
	        GPS
	    }

	    @Enumerated(EnumType.STRING)
	    private TransportAttendanceStatus pickupStatus;
	    public enum TransportAttendanceStatus 
	    {
	        PICKED_UP,
	        DROPPED,
	        ABSENT,
	        SKIPPED
	    }


	    @Enumerated(EnumType.STRING)
	    private TransportAttendanceStatus dropStatus;
	}

