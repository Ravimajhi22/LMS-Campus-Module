package com.campusFacilities.www.model.Hostel;

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
import jakarta.persistence.UniqueConstraint;
import lombok.Data;

       @Entity
	@Table(name = "hostel_rooms", uniqueConstraints = {@UniqueConstraint(columnNames = {"block_id","room_number"})})
	@Data
       public class HostelRoom {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long roomId;

	    @ManyToOne
	    @JoinColumn(name = "block_id", nullable = false)
	    private HostelBlock block;

	    private Integer floorNumber;
	    
	    @Column(nullable = false)
	    private String roomNumber;

	    @Enumerated(EnumType.STRING)
	    private RoomType roomType; 

	    private Integer capacity;
	    private Integer occupiedCount = 0;

	    @Enumerated(EnumType.STRING)
	    private RoomStatus status = RoomStatus.AVAILABLE;

	    private Boolean isDeleted = false;
	    
	    public enum RoomType {
	        SINGLE,
	        DOUBLE,
	        TRIPLE
	    }

	    public enum RoomStatus {
	        AVAILABLE,
	        FULL,
	        MAINTENANCE
	    }
	}

