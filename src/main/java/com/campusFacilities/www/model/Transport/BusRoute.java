package com.campusFacilities.www.model.Transport;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
	@Table(name = "bus_route")
	@Data
	public class BusRoute {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long busRouteId;

	    @ManyToOne
	    @JoinColumn(name = "bus_id", nullable = false)
	    private Bus bus;

	    @ManyToOne
	    @JoinColumn(name = "route_id", nullable = false)
	    private RouteWay route;
	}


