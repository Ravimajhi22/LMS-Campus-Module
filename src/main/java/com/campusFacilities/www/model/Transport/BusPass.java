package com.campusFacilities.www.model.Transport;
import java.time.LocalDate;

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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "bus_pass")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusPass {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long passId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "issue_date")
    private LocalDate issueDate;

    @Column(name = "expiry_date")
    private LocalDate expiryDate;

    @Column(name = "pickup_point")
    private String pickUp;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private PassStatus status = PassStatus.ACTIVE;

    @ManyToOne
    @JoinColumn(name = "bus_id", nullable = false)
    private Bus bus;
   
   
     public enum PassStatus {
        ACTIVE,
        EXPIRED,
        CANCELLED
    }
        public void setStatus(PassStatus status) {
            this.status = status;
        }
    }


