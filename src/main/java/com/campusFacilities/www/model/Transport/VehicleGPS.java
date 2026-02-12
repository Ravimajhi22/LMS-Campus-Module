package com.campusFacilities.www.model.Transport;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "vehicle_gps",
       indexes = @Index(name = "idx_vehicle_timestamp",
                        columnList = "vehicle_id, timestamp"))
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VehicleGPS {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_id", nullable = false)
    private Vehicle vehicle;

    @Column(nullable = false)
    private double latitude;

    @Column(nullable = false)
    private double longitude;

    @Column(nullable = false)
    private double speed;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private VehicleGPSStatus status;
    public enum VehicleGPSStatus {
        ACTIVE, STOPPED, IDLE, OFFLINE
    }

    @CreationTimestamp
    private LocalDateTime timestamp;
}