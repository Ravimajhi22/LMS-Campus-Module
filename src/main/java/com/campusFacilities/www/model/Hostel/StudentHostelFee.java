package com.campusFacilities.www.model.Hostel;
import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "student_Hostel_fees")
@Data
public class StudentHostelFee {
	

	  @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long feeId;

	    @Column(nullable = true)
	    private Long studentId;

	    @Column(nullable = false)
	    private String studentName;

	    @Column(nullable = false)
	    private Double monthlyFee;

	    @Column(nullable = false)
	    private Double totalFee;

	    @Column(nullable = false)
	    private Double amountPaid;

	    @Column(nullable = false)
	    private Double dueAmount;

	    private LocalDate lastPaymentDate;

	   
	    @Enumerated(EnumType.STRING)
	    @Column(nullable = false)
	    private FeeStatus status;

	    public enum FeeStatus {
	        PAID,
	        PARTIALLY_PAID,
	        DUE
	    }
	}

