package com.campusFacilities.www.model.Hostel;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "hostel_fees")
@Data
public class HostelFee 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long feeId;

    @Enumerated(EnumType.STRING)
    private RoomType roomType;
   
    public enum RoomType {
        SINGLE,
        DOUBLE,
        TRIPLE
    }

    
    private BigDecimal amount;
    private LocalDate effectiveFrom;

    @Enumerated(EnumType.STRING)
    private Status status = Status.ACTIVE;
    public enum Status {
        ACTIVE,
        INACTIVE,
        AVAILABLE,
        FULL,
        MAINTENANCE
    }

}
