package com.campusFacilities.www.model.Transport;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
	@Table(name = "transport_attendance")
	@Data
	public class TransportAttendance {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    /* ================= STUDENT ================= */
	   
	    private Long studentId;

	    /* ================= VEHICLE (BY VEHICLE NUMBER) ================= */
	   
	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "vehicle_id", nullable = false) 
	    private Vehicle vehicle;


	    /* ================= DATE ================= */
	    @Column(name = "attendance_date", nullable = false)
	    private LocalDate attendanceDate;
	   
	    @Enumerated(EnumType.STRING)
	    private MarkedBy markedBy;
	    
	    public enum MarkedBy {
	        MANUAL,
	        QRCODE
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


		public void save(TransportAttendance attendance) {
			
			
		}


		public void setRoute(RouteWay orElseThrow) {
			
		}
	}

