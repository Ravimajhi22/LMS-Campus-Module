package com.campusFacilities.www.model.Library;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "fine_slabs")
@Data
public class FineSlab {
	
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @JsonBackReference
	    @ManyToOne
	    @JoinColumn(name = "library_settings_id", nullable = false)
	    private LibrarySettings librarySettings;

	    @Column(name = "member_role")
	    private String memberRole;

	    @Column(name = "from_day", nullable = false)
	    private Integer fromDay;

	    @Column(name = "to_day", nullable = false)
	    private Integer toDay;

	    @Column(name = "fine_per_day", nullable = false)
	    private Double finePerDay;

	    @Column(name = "slab_order")
	    private Integer slabOrder;
	}



