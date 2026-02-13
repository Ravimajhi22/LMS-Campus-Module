package com.campusFacilities.www.model.Transport;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;

@Entity
@Table(name = "transport_fee_structure",
       uniqueConstraints = @UniqueConstraint(columnNames = {"route_id"}))
@Data
public class TransportFeeStructure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "route_id", nullable = false, unique = true)
    private Long routeId;


      @Column(nullable = false, precision = 10, scale = 2)
      private BigDecimal annualFee;

    @Column(name = "academic_year", length = 10)
    private String academicYear; 
}