package com.campusFacilities.www.model.Hostel;





import com.fasterxml.jackson.annotation.JsonIgnore;


import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "hostel_complaints")
@Data
public class HostelComplaint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long complaintId;

	/*
	 * @ManyToOne
	 * 
	 * @JoinColumn(name = "user_id", nullable = false) private User user;
	 */

    @ManyToOne
    @JoinColumn(name = "room_id", nullable = false)
    private HostelRoom room;

    private String complaintType;
    private String description;

    @Enumerated(EnumType.STRING)
    private ComplaintStatus status = ComplaintStatus.OPEN;
    
    public enum ComplaintStatus {
        OPEN,
        IN_PROGRESS,
        RESOLVED
    }
    @JsonIgnore
    private String internalNotes;

	/*
	 * @CreationTimestamp
	 * 
	 * @Column(name = "created_at", updatable = false) private Timestamp createdAt;
	 */

}
