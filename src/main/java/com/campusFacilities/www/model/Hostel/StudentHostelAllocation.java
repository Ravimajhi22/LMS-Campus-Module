package com.campusFacilities.www.model.Hostel;

import java.time.LocalDate;
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
@Table(name = "student_hostel_allocations")
@Data
public class StudentHostelAllocation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long allocationId;

	/*
	 * @ManyToOne
	 * 
	 * @JoinColumn(name = "user_id", nullable = false) private User user;
	 */
    
    @ManyToOne
    @JoinColumn(name = "room_id", nullable = false)
    private HostelRoom room;

    private LocalDate joinDate;
    private LocalDate leaveDate;

    @Enumerated(EnumType.STRING)
    private AllocationStatus status = AllocationStatus.ACTIVE;
    
    public enum AllocationStatus
    {
        ACTIVE,
        CHECKED_OUT,
        CANCELLED
    }

	/*
	 * @CreationTimestamp private Timestamp createdAt;
	 */
}
