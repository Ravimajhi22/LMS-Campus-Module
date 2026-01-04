package com.campusFacilities.www.model.Hostel;
import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
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
@Table(name = "hostel")
@Data
public class Hostel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hostelId;

    @Column(unique = true, nullable = false)
    private String hostelName;

    @Enumerated(EnumType.STRING)
    private HostelType hostelType; 

    private Integer totalBlocks = 0;
    private Integer totalRooms = 0;
    private String wardenName;
    private String contactNumber;

    @Enumerated(EnumType.STRING)
    private Status status = Status.ACTIVE;

    private Boolean isDeleted = false;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public enum HostelType 
    {
        BOYS,
        GIRLS,
        MIXED
    }

    public enum Status 
    {
        ACTIVE,
        INACTIVE
    }
}


