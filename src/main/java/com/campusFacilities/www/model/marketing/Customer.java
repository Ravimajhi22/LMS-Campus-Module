package com.campusFacilities.www.model.marketing;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

   @Entity
   @Table(name = "customer")
   @Data
   
	public class Customer {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "customer_id")
	    private Integer customerId;

	    @Column(name = "name", length = 100, nullable = false)
	    private String name;

	    @Column(name = "email", length = 150, nullable = false, unique = true)
	    private String email;

	    @Column(name = "phone", length = 15)
	    private String phone;

	    @Column(name = "password", length = 255, nullable = false)
	    private String password; // Encrypted password

	    @Column(name = "location", length = 100)
	    private String location;

	    @Column(name = "created_date")
	    private LocalDate createdDate;

	}

