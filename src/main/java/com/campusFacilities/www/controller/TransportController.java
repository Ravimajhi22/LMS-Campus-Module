package com.campusFacilities.www.controller;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.campusFacilities.www.model.Transport.Vehicle;
import com.campusFacilities.www.model.Transport.RouteWay;
import com.campusFacilities.www.service.Imp.TransportService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/transport")
@RequiredArgsConstructor
public class TransportController {

    @Autowired
    private TransportService transportService;


    // ============================= BUS DETAILS ===================================//

    @PostMapping("/bus")
    @PreAuthorize("hasAuthority('BUS_CREATE')")
    public ResponseEntity<?> addBus(@RequestBody Vehicle bus) {
        return ResponseEntity.ok(transportService.addBus(bus));
    }
    
    
    @GetMapping("/buses")
    @PreAuthorize("hasAuthority('BUS_VIEW')")
    public List<Vehicle> getAllBuses() {
        return transportService.getAllBuses();
    }

    @PutMapping("/bus/{busId}")
    @PreAuthorize("hasAuthority('BUS_UPDATE')")
    public ResponseEntity<?> updateBus(
            @PathVariable Long busId,
            @RequestBody Vehicle bus) {

        return ResponseEntity.ok(
                transportService.updateBus(busId, bus)
        );
    }

    @DeleteMapping("/bus/{busId}")
    @PreAuthorize("hasAuthority('BUS_DELETE')")
    public ResponseEntity<?> deleteBus(@PathVariable Long busId) {

        transportService.deleteBus(busId);
        return ResponseEntity.ok("Bus deleted successfully");
    }

    
    @PatchMapping("/bus/{busId}")
    @PreAuthorize("hasAuthority('BUS_UPDATE')")
    public ResponseEntity<?> patchBus(
            @PathVariable Long busId,
            @RequestBody Map<String, Object> updates) {

        Vehicle bus = new Vehicle();
     
        if (updates.containsKey("capacity"))
            bus.setCapacity((Integer) updates.get("capacity"));

        return ResponseEntity.ok(
                transportService.patchBus(busId, bus)
        );
    }

   // ================================== BUS ROUTE================================================//

    @PostMapping("/route")
    @PreAuthorize("hasAuthority('ROUTE_CREATE')")
    public ResponseEntity<RouteWay> addRoute(@RequestBody RouteWay routeWay) {
        return ResponseEntity.ok(transportService.addRoute(routeWay));
    }


    @GetMapping("/routes")
    @PreAuthorize("hasAuthority('ROUTE_VIEW')")
    public ResponseEntity<List<RouteWay>> getAllRoutes() {
        return ResponseEntity.ok(transportService.getAllRoutes());
    }
    
    @PutMapping("/route/{routeId}")
    @PreAuthorize("hasAuthority('ROUTE_UPDATE')")
    public ResponseEntity<RouteWay> updateRoute(
            @PathVariable Long routeId,
            @RequestBody RouteWay routeWay) {
        return ResponseEntity.ok(transportService.updateRoute(routeId, routeWay));
    }


    @DeleteMapping("/route/{routeId}")
    @PreAuthorize("hasAuthority('ROUTE_DELETE')")
    public ResponseEntity<String> deleteRoute(@PathVariable Long routeId) {
        transportService.deleteRoute(routeId);
        return ResponseEntity.ok("Route deleted successfully");
    }

    @PatchMapping("/route/{routeId}")
    @PreAuthorize("hasAuthority('ROUTE_UPDATE')")
    public ResponseEntity<RouteWay> patchRoute(
            @PathVariable Long routeId,
            @RequestBody Map<String, Object> updates) {

        RouteWay route = new RouteWay();

        if (updates.containsKey("routeName"))
            route.setRouteName((String) updates.get("routeName"));

 
        return ResponseEntity.ok(transportService.patchRoute(routeId, route));
    }
        //=============================BUS GPS===================================//
   
       
        }

    
