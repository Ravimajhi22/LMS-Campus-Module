package com.campusFacilities.www.service.Imp;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.campusFacilities.www.model.Transport.Vehicle;
import com.campusFacilities.www.model.Transport.RouteWay;
import com.campusFacilities.www.repository.Transport.BusGPSRepository;
import com.campusFacilities.www.repository.Transport.BusRepository;
import com.campusFacilities.www.repository.Transport.RouteWayRepository;


@Service
public class TransportService {

    @Autowired
    private BusRepository busRepository;

    @Autowired
    private RouteWayRepository routeWayRepository;    
    
    
    // ======================== BUS =========================//

    public Vehicle addBus(Vehicle bus) {
        RouteWay route = routeWayRepository.findById(bus.getRoute().getRouteId())
                .orElseThrow(() -> new RuntimeException("Route not found"));
        bus.setRoute(route);
        return busRepository.save(bus);
    }

    public List<Vehicle> getAllBuses() {
        return busRepository.findAll();
    }

    public Vehicle updateBus(Long busId, Vehicle bus) {
        Vehicle existing = busRepository.findById(busId)
                .orElseThrow(() -> new RuntimeException("Bus not found"));
        existing.setCapacity(bus.getCapacity());
        existing.setRoute(bus.getRoute());

        return busRepository.save(existing);
    }

    public Vehicle patchBus(Long busId, Vehicle bus) {
        Vehicle existing = busRepository.findById(busId)
                .orElseThrow(() -> new RuntimeException("Bus not found"));

 
        if (bus.getCapacity() != null)
            existing.setCapacity(bus.getCapacity());

        if (bus.getRoute() != null && bus.getRoute().getRouteId() != null) {
            RouteWay route = routeWayRepository.findById(bus.getRoute().getRouteId())
                    .orElseThrow(() -> new RuntimeException("Route not found"));
            existing.setRoute(route);
        }

        return busRepository.save(existing);
    }

    public void deleteBus(Long busId) {
        busRepository.deleteById(busId);
    }

    // ================= ROUTE =================

    public RouteWay addRoute(RouteWay routeWay) {
        return routeWayRepository.save(routeWay);
    }

    public List<RouteWay> getAllRoutes() {
        return routeWayRepository.findAll();
    }

    public RouteWay updateRoute(Long routeId, RouteWay routeWay) {
        RouteWay existing = routeWayRepository.findById(routeId)
                .orElseThrow(() -> new RuntimeException("Route not found"));

        existing.setRouteName(routeWay.getRouteName());
        return routeWayRepository.save(existing);
    }

    public RouteWay patchRoute(Long routeId, RouteWay routeWay) {
        RouteWay existing = routeWayRepository.findById(routeId)
                .orElseThrow(() -> new RuntimeException("Route not found"));

        if (routeWay.getRouteName() != null)
            existing.setRouteName(routeWay.getRouteName());

      
        return routeWayRepository.save(existing);
    }

    public void deleteRoute(Long routeId) {
        routeWayRepository.deleteById(routeId);
    }
    //================BUS GPS==========================
    
    @GetMapping("/gps/bus/{busId}")
    public BusGPSRepository getLatestLocation(@PathVariable Long busId) {
        return BusGPSRepository.findTopByBusIdOrderByTimestampDesc(busId);
    }
}
