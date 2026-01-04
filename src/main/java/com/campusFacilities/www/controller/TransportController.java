package com.campusFacilities.www.controller;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
import com.campusFacilities.www.model.Transport.BusPass;
import com.campusFacilities.www.model.Transport.BusRoute;
import com.campusFacilities.www.model.Transport.RouteWay;
import com.campusFacilities.www.model.Transport.Stop;
import com.campusFacilities.www.service.Imp.TransportService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/transport")
public class TransportController {

    @Autowired
    private TransportService transportService;


    // ================= BUS =================//

    @PostMapping("/bus")
    public ResponseEntity<?> addBus(HttpServletRequest request,
                                    @RequestBody Bus bus) {

        List<String> permissions =
                (List<String>) request.getAttribute("permissions");

        if (!permissions.contains("BUS_CREATE")) {
            return ResponseEntity.status(403).body("Access Denied");
        }

        return ResponseEntity.ok(transportService.addBus(bus));
    }


    @GetMapping("/buses")
    public ResponseEntity<List<Bus>> getAllBuses() {
        return ResponseEntity.ok(transportService.getAllBuses());
    }

    @PutMapping("/bus/{busId}")
    public ResponseEntity<Bus> updateBus(@PathVariable Long busId, @RequestBody Bus bus) {
        return ResponseEntity.ok(transportService.updateBus(busId, bus));
    }

    @DeleteMapping("/bus/{busId}")
    public ResponseEntity<String> deleteBus(@PathVariable Long busId) {
        transportService.deleteBus(busId);
        return ResponseEntity.ok("Bus deleted successfully");
    }
    @PatchMapping("/bus/{busId}")
    public ResponseEntity<Bus> patchBus(@PathVariable Long busId, @RequestBody Map<String,Object> updates) {
        Bus bus = new Bus();
        if(updates.containsKey("busNumber")) bus.setBusNumber((String) updates.get("busNumber"));
        if(updates.containsKey("driverName")) bus.setDriverName((String) updates.get("driverName"));
        if(updates.containsKey("driverContact")) bus.setDriverContact((String) updates.get("driverContact"));
        if(updates.containsKey("capacity")) bus.setCapacity((Integer) updates.get("capacity"));

        return ResponseEntity.ok(transportService.patchBus(busId, bus));
    }



    // ================= ROUTE =================//

    @PostMapping("/route")
    public ResponseEntity<RouteWay> addRoute(@RequestBody RouteWay routeWay) {
        return ResponseEntity.ok(transportService.addRoute(routeWay));
    }

    @GetMapping("/routes")
    public ResponseEntity<List<RouteWay>> getAllRoutes() {
        return ResponseEntity.ok(transportService.getAllRoutes());
    }

    @PutMapping("/route/{routeId}")
    public ResponseEntity<RouteWay> updateRoute(
            @PathVariable Long routeId,
            @RequestBody RouteWay routeWay) {
        return ResponseEntity.ok(transportService.updateRoute(routeId, routeWay));
    }

    @DeleteMapping("/route/{routeId}")
    public ResponseEntity<String> deleteRoute(@PathVariable Long routeId) {
        transportService.deleteRoute(routeId);
        return ResponseEntity.ok("Route deleted successfully");
    }
    @PatchMapping("/route/{routeId}")
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


    // ================= STOP =================//

    @PostMapping("/stop")
    public ResponseEntity<Stop> addStop(@RequestBody Stop stop) {
        return ResponseEntity.ok(transportService.addStop(stop));
    }

    @GetMapping("/stop/route/{routeId}")
    public ResponseEntity<List<Stop>> getStopsByRoute(@PathVariable Long routeId) {
        return ResponseEntity.ok(transportService.getStopsByRoute(routeId));
    }

    @PutMapping("/stop/{stopId}")
    public ResponseEntity<Stop> updateStop(
            @PathVariable Long stopId,
            @RequestBody Stop stop) {
        return ResponseEntity.ok(transportService.updateStop(stopId, stop));
    }

    @DeleteMapping("/stop/{stopId}")
    public ResponseEntity<String> deleteStop(@PathVariable Long stopId) {
        transportService.deleteStop(stopId);
        return ResponseEntity.ok("Stop deleted successfully");
    }
    @PatchMapping("/stop/{stopId}")
    public ResponseEntity<Stop> patchStop(
            @PathVariable Long stopId,
            @RequestBody Map<String, Object> updates) {

        Stop stop = new Stop();

        if (updates.containsKey("stopName"))
            stop.setStopName((String) updates.get("stopName"));

        
        return ResponseEntity.ok(transportService.patchStop(stopId, stop));
    }

    // ================= BUS PASS =================

    @PostMapping("/buspass")
    public ResponseEntity<BusPass> addBusPass(@RequestBody BusPass busPass) {
        return ResponseEntity.ok(transportService.addBusPass(busPass));
    }

    @GetMapping("/buspasses")
    public ResponseEntity<List<BusPass>> getAllBusPasses() {
        return ResponseEntity.ok(transportService.getAllBusPasses());
    }

    @PutMapping("/buspass/{passId}")
    public ResponseEntity<BusPass> updateBusPass(
            @PathVariable Long passId,
            @RequestBody BusPass busPass) {
        return ResponseEntity.ok(transportService.updateBusPass(passId, busPass));
    }

    @DeleteMapping("/buspass/{passId}")
    public ResponseEntity<String> deleteBusPass(@PathVariable Long passId) {
        transportService.deleteBusPass(passId);
        return ResponseEntity.ok("Bus pass deleted successfully");
    }
    @PatchMapping("/buspass/{passId}")
    public ResponseEntity<BusPass> patchBusPass(
            @PathVariable Long passId,
            @RequestBody Map<String, Object> updates) {

        BusPass pass = new BusPass();

        return ResponseEntity.ok(transportService.patchBusPass(passId, pass));
    }

  //==================== BusRouteMapping ========================

    @PostMapping("/busroute")
    public ResponseEntity<BusRoute> addBusRoute(@RequestBody BusRoute busRoute) {
        return ResponseEntity.ok(transportService.saveBusRoute(busRoute));
    }

    @GetMapping("/busroutes")
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
    public ResponseEntity<String> deleteBusRoute(@PathVariable Long id) {
        transportService.deleteBusRoute(id);
        return ResponseEntity.ok("Bus route mapping deleted successfully");
        
    }
    @GetMapping("/busroute/bus/{busId}")
    public ResponseEntity<List<BusRoute>> getByBusId(@PathVariable Long busId) {
        return ResponseEntity.ok(transportService.getByBusId(busId));
    }

    @GetMapping("/busroute/route/{routeId}")
    public ResponseEntity<List<BusRoute>> getByRouteId(@PathVariable Long routeId) {
        return ResponseEntity.ok(transportService.getByRouteId(routeId));
}
    @PatchMapping("/busroute/{id}")
    public ResponseEntity<BusRoute> patchBusRoute(
            @PathVariable Long id,
            @RequestBody Map<String, Object> updates) {

        BusRoute busRoute = new BusRoute();

    
        return ResponseEntity.ok(transportService.patchBusRoute(id, busRoute));
    }

}