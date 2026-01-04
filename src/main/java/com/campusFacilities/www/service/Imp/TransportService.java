package com.campusFacilities.www.service.Imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.campusFacilities.www.model.Transport.*;
import com.campusFacilities.www.repository.*;

@Service
public class TransportService {

    @Autowired
    private BusRepository busRepository;

    @Autowired
    private RouteWayRepository routeWayRepository;

    @Autowired
    private StopRepository stopRepository;

    @Autowired
    private BusPassRepository busPassRepository;

    @Autowired
    private BusRouteMappingRepository busRouteMappingRepository;

    // ================= BUS =================

    public Bus addBus(Bus bus) {
        RouteWay route = routeWayRepository.findById(bus.getRoute().getRouteId())
                .orElseThrow(() -> new RuntimeException("Route not found"));
        bus.setRoute(route);
        return busRepository.save(bus);
    }

    public List<Bus> getAllBuses() {
        return busRepository.findAll();
    }

    public Bus updateBus(Long busId, Bus bus) {
        Bus existing = busRepository.findById(busId)
                .orElseThrow(() -> new RuntimeException("Bus not found"));

        existing.setBusNumber(bus.getBusNumber());
        existing.setDriverName(bus.getDriverName());
        existing.setDriverContact(bus.getDriverContact());
        existing.setCapacity(bus.getCapacity());
        existing.setRoute(bus.getRoute());

        return busRepository.save(existing);
    }

    public Bus patchBus(Long busId, Bus bus) {
        Bus existing = busRepository.findById(busId)
                .orElseThrow(() -> new RuntimeException("Bus not found"));

        if (bus.getBusNumber() != null)
            existing.setBusNumber(bus.getBusNumber());

        if (bus.getDriverName() != null)
            existing.setDriverName(bus.getDriverName());

        if (bus.getDriverContact() != null)
            existing.setDriverContact(bus.getDriverContact());

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
        existing.setStartPoint(routeWay.getStartPoint());
        existing.setEndPoint(routeWay.getEndPoint());

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

    // ================= STOP =================

    public Stop addStop(Stop stop) {
        return stopRepository.save(stop);
    }

    public List<Stop> getStopsByRoute(Long routeId) {
        return stopRepository.findByRouteRouteIdOrderBySequenceNumber(routeId);
    }

    public Stop updateStop(Long stopId, Stop stop) {
        Stop existing = stopRepository.findById(stopId)
                .orElseThrow(() -> new RuntimeException("Stop not found"));

        existing.setStopName(stop.getStopName());
        existing.setSequenceNumber(stop.getSequenceNumber());
        existing.setRoute(stop.getRoute());

        return stopRepository.save(existing);
    }

    public Stop patchStop(Long stopId, Stop stop) {
        Stop existing = stopRepository.findById(stopId)
                .orElseThrow(() -> new RuntimeException("Stop not found"));

        if (stop.getStopName() != null)
            existing.setStopName(stop.getStopName());


        return stopRepository.save(existing);
    }

    public void deleteStop(Long stopId) {
        stopRepository.deleteById(stopId);
    }

    // ================= BUS PASS =================

    public BusPass addBusPass(BusPass busPass) {
        return busPassRepository.save(busPass);
    }

    public List<BusPass> getAllBusPasses() {
        return busPassRepository.findAll();
    }

    public BusPass updateBusPass(Long passId, BusPass busPass) {
        BusPass existing = busPassRepository.findById(passId)
                .orElseThrow(() -> new RuntimeException("Bus pass not found"));

        existing.setExpiryDate(busPass.getExpiryDate());
        existing.setPickUp(busPass.getPickUp());
        existing.setStatus(busPass.getStatus());
        existing.setBus(busPass.getBus());

        return busPassRepository.save(existing);
    }

    public BusPass patchBusPass(Long passId, BusPass busPass) {
        BusPass existing = busPassRepository.findById(passId)
                .orElseThrow(() -> new RuntimeException("Bus pass not found"));

 

        if (busPass.getStatus() != null)
            existing.setStatus(busPass.getStatus());

        return busPassRepository.save(existing);
    }

    public void deleteBusPass(Long passId) {
        busPassRepository.deleteById(passId);
    }

    // ================= BUS ROUTE =================

    public BusRoute saveBusRoute(BusRoute busRoute) {
        return busRouteMappingRepository.save(busRoute);
    }

    public List<BusRoute> getAllBusRoutes() {
        return busRouteMappingRepository.findAll();
    }

    public Optional<BusRoute> getBusRouteById(Long id) {
        return busRouteMappingRepository.findById(id);
    }

    public BusRoute patchBusRoute(Long id, BusRoute busRoute) {
        BusRoute existing = busRouteMappingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("BusRoute not found"));

       
        return busRouteMappingRepository.save(existing);
    }

    public void deleteBusRoute(Long id) {
        busRouteMappingRepository.deleteById(id);
    }

    public List<BusRoute> getByBusId(Long busId) {
        return busRouteMappingRepository.findByBus_BusId(busId);
    }

    public List<BusRoute> getByRouteId(Long routeId) {
        return busRouteMappingRepository.findByRoute_RouteId(routeId);
    }
}
