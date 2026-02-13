package com.campusFacilities.www.model.Transport;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "transport_payments")
@Data
public class TransportPayments {

    @Id
    @Column(length = 50)
    private String id;   	

    @Column(name = "student_id", nullable = false)
    private Long studentId;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;

    @Column(name = "payment_date")
    private LocalDate paymentDate = LocalDate.now();

	
	  @Enumerated(EnumType.STRING)
	 
	  @Column(name = "payment_mode") private PaymentMode paymentMode;
	  public enum PaymentMode {
		    CASH,
		    UPI,
		    CARD,
		    CHEQUE,
		    BANK_TRANSFER
		}
	 
    @Column(name = "reference_no", length = 50)
    private String referenceNo;

    @Column(columnDefinition = "TEXT")
    private String remarks;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();
}
