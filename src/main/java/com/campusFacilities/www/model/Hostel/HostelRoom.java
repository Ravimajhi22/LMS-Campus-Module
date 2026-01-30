package com.campusFacilities.www.model.Hostel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

	 
	    @Column(name = "room_number", nullable = false)
	    private String roomNumber;
	    
	   
	    @Enumerated(EnumType.STRING)
	    @Column(name = "sharing_type", nullable = false)
	    private SharingType sharingType;
	    
	    public enum SharingType {
	        SINGLE,   // 1 bed
	        DOUBLE,   // 2 beds
	        TRIPLE,   // 3 beds
	        QUAD      // 4 beds
	    }


	    @Enumerated(EnumType.STRING)
	    private RoomStatus status = RoomStatus.AVAILABLE;

	    public enum RoomStatus {
	        AVAILABLE,
	        PARTIALLY_FILLED,
	        FULL
	    }
	    // ---------- AUTO CALCULATED ----------
	    @Column(name = "currently_occupied", nullable = false)
	    private int currentlyOccupied = 0;

	    @Column(name = "is_deleted")
	    private Boolean isDeleted = false;

	  


	    
	}

