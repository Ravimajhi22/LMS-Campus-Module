package com.campusFacilities.www.model.Transport;

import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "route_way")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RouteWay 

{
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;;
    
    @Column(name = "route_Code")
    private Long routeCode;

    @Column(name = "route_name", nullable = false)
    private String routeName;
    
    @ElementCollection
    @CollectionTable(name = "route_pickup_points", joinColumns = @JoinColumn(name = "route_id"))
    @Column(name = "pickup_point")
    private List<String> pickupPoints;

    @ElementCollection
    @CollectionTable(name = "route_drop_points", joinColumns = @JoinColumn(name = "route_id"))
    @Column(name = "drop_point")
    private List<String> dropPoints;
    
    private Double distanceKm;
    
    private Integer estimatedTimeMinutes;
    

    /* =============VEHICLES ASSIGNED TO THIS ROUTE =========*/
  
    @OneToMany(mappedBy = "route")
    private List<Vehicle> vehicles;

    
    @Column(nullable = false)
    private Boolean active = true;


    
}
