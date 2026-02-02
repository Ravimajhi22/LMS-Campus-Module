package com.campusFacilities.www.model.Hostel;
import java.math.BigDecimal;
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
@Table(name = "student_hostel_allocations")
@Data
public class StudentHostelAllocation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long allocationId;
    
    @Column(nullable = false)
    private Long studentId;

    private String studentName;
    private String studentEmail;
    
    private String fatherName;
    private String fatherPhone;
    
    
    // ---------------- Hostel Mapping ----------------
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hostel_id", nullable = false)
    private Hostel hostel;
    
    @ManyToOne(fetch = FetchType.LAZY)
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
    
    @Column(nullable = false)
    private BigDecimal monthlyFee;      

    @Column(nullable = false)
    private BigDecimal totalFee;       

    @Column(nullable = false)
    private BigDecimal amountPaid = BigDecimal.ZERO;  

    @Column(nullable = false)
    private BigDecimal dueAmount;     

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus = PaymentStatus.DUE; 

    public enum PaymentStatus 
    {
        PAID,
        DUE
    }

    private LocalDate lastPaymentDate;

}
