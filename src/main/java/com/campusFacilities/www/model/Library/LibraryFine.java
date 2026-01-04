package com.campusFacilities.www.model.Library;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "library_fines")
@Data
public class LibraryFine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fineId;

    @OneToOne
    @JoinColumn(name = "issue_id", nullable = false)
    private BookIssueRecord issueRecord;

    private Double fineAmount;

    @Enumerated(EnumType.STRING)
    private Status paidStatus = Status.UNPAID;

    private LocalDateTime createdAt = LocalDateTime.now();

    public enum Status { PAID, UNPAID }
    
    private Boolean isDeleted = false;

  
}
