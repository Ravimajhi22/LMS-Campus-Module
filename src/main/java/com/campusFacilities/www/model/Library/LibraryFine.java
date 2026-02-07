package com.campusFacilities.www.model.Library;

import java.time.LocalDateTime;

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
import lombok.Data;

@Entity
@Table(name = "library_fines")
@Data
public class LibraryFine {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fine_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "issue_id", nullable = false)
    private BookIssueRecord issueRecord;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    private Double fineAmount;

    @Enumerated(EnumType.STRING)
    private Status paidStatus = Status.UNPAID;

    private Boolean isDeleted = false;

    public enum Status {
        PAID,
        UNPAID
    }

	public void setCreatedAt(LocalDateTime now) {
		// TODO Auto-generated method stub
		
	}
}
