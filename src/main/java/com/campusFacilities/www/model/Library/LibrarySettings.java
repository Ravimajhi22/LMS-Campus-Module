package com.campusFacilities.www.model.Library;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "library_settings")
@Data
public class LibrarySettings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long settingId;
    private Integer maxBooksStudent = 3;
    private Integer maxBooksStaff = 5;
    private Integer maxIssueDays = 14;
    private Double finePerDay = 2.0;
    private Boolean isDeleted = false;   
    private LocalDateTime updatedAt = LocalDateTime.now();
   

}
