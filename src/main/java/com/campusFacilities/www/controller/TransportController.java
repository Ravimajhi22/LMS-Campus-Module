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
import com.campusFacilities.www.model.Transport.Bus;
import com.campusFacilities.www.model.Transport.BusRoute;
import com.campusFacilities.www.model.Transport.RouteWay;
import com.campusFacilities.www.model.Transport.Stop;
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
    public ResponseEntity<?> addBus(@RequestBody Bus bus) {
        return ResponseEntity.ok(transportService.addBus(bus));
    }
    
    
    @GetMapping("/buses")
    @PreAuthorize("hasAuthority('BUS_VIEW')")
    public List<Bus> getAllBuses() {
        return transportService.getAllBuses();
    }

    @PutMapping("/bus/{busId}")
    @PreAuthorize("hasAuthority('BUS_UPDATE')")
    public ResponseEntity<?> updateBus(
            @PathVariable Long busId,
            @RequestBody Bus bus) {

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

        Bus bus = new Bus();

        if (updates.containsKey("busNumber"))
            bus.setBusNumber((String) updates.get("busNumber"));

     
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

        if (updates.containsKey("description"))
            route.setDescription((String) updates.get("description"));

        return ResponseEntity.ok(transportService.patchRoute(routeId, route));
    }

 //===================================== STOP's Stations==============================================//

    @PostMapping("/stop")
    @PreAuthorize("hasAuthority('STOP_POINT_CREATE')")
    public ResponseEntity<Stop> addStop(@RequestBody Stop stop) {
        return ResponseEntity.ok(transportService.addStop(stop));
    }
    
    @GetMapping("/stop/route/{routeId}")
    @PreAuthorize("hasAuthority('STOP_POINT_VIEW')")
    public ResponseEntity<List<Stop>> getStopsByRoute(@PathVariable Long routeId) {
        return ResponseEntity.ok(transportService.getStopsByRoute(routeId));
    }
    
    @PutMapping("/stop/{stopId}")
    @PreAuthorize("hasAuthority('STOP_POINT_UPDATE')")
    public ResponseEntity<Stop> updateStop(
            @PathVariable Long stopId,
            @RequestBody Stop stop) {
        return ResponseEntity.ok(transportService.updateStop(stopId, stop));
    }

    @DeleteMapping("/stop/{stopId}")
    @PreAuthorize("hasAuthority('STOP_POINT_DELETE')")
    public ResponseEntity<String> deleteStop(@PathVariable Long stopId) {
        transportService.deleteStop(stopId);
        return ResponseEntity.ok("Stop deleted successfully");
    }
    
    @PatchMapping("/stop/{stopId}")
    @PreAuthorize("hasAuthority('STOP_POINT_UPDATE')")
    public ResponseEntity<Stop> patchStop(
            @PathVariable Long stopId,
            @RequestBody Map<String, Object> updates) {

        Stop stop = new Stop();

        if (updates.containsKey("stopName")) {
            stop.setStopName((String) updates.get("stopName"));
        }

        return ResponseEntity.ok(transportService.patchStop(stopId, stop));
    }
    
	
  //==================================== BusRouteMapping ===========================================//

    @PostMapping("/busroute")
    @PreAuthorize("hasAuthority('BUS_ROUTE_CREATE')")
    public ResponseEntity<BusRoute> addBusRoute(@RequestBody BusRoute busRoute) {
        return ResponseEntity.ok(transportService.saveBusRoute(busRoute));
    }

    @GetMapping("/busroutes")
    @PreAuthorize("hasAuthority('BUS_ROUTE_VIEW')")
    public ResponseEntity<List<BusRoute>> getAllBusRoutes() {
        return ResponseEntity.ok(transportService.getAllBusRoutes());
    }

    @GetMapping("/busroute/{id}")
    public ResponseEntity<BusRoute> getBusRouteById(@PathVariable Long id) {
        return transportService.getBusRouteById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/busroute/{id}")
    @PreAuthorize("hasAuthority('BUS_ROUTE_DELETE')")
    public ResponseEntity<String> deleteBusRoute(@PathVariable Long id) {
        transportService.deleteBusRoute(id);
        return ResponseEntity.ok("Bus route mapping deleted successfully");
    }
   
    @PatchMapping("/busroute/{id}")
    @PreAuthorize("hasAuthority('BUS_ROUTE_UPDATE')")
    public ResponseEntity<BusRoute> patchBusRoute(
            @PathVariable Long id,
            @RequestBody Map<String, Object> updates) {

        BusRoute busRoute = new BusRoute();
        return ResponseEntity.ok(transportService.patchBusRoute(id, busRoute));
        
        //=============================BUS GPS===================================//
   
       
        }

    }
