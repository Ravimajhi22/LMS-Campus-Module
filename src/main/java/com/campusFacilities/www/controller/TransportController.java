package com.campusFacilities.www.controller;

import java.time.LocalDate;
import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.campusFacilities.www.Transport.util.QRCodeUtil;
import com.campusFacilities.www.model.Transport.ConductorDetails;
import com.campusFacilities.www.model.Transport.DriverDetails;
import com.campusFacilities.www.model.Transport.QRAttendanceRequest;
import com.campusFacilities.www.model.Transport.RouteWay;
import com.campusFacilities.www.model.Transport.Vehicle;
import com.campusFacilities.www.model.Transport.VehicleGPS;
import com.campusFacilities.www.service.Imp.TransportService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/transport")
@RequiredArgsConstructor
public class TransportController {

    @Autowired
    private TransportService transportService;

    /*
     * =====================================================
     * VEHICLE
     * =====================================================
     */

    // @PreAuthorize("hasAuthority('VEHICLE_ADD')")
    @PostMapping("/vehicles")
    public ResponseEntity<Vehicle> addVehicle(@RequestBody Vehicle vehicle) {
        return ResponseEntity.ok(transportService.addVehicle(vehicle));
    }

    // @PreAuthorize("hasAuthority('VEHICLE_VIEW')")
    @GetMapping("/vehicles")
    public ResponseEntity<List<Vehicle>> getAllVehicles() {
        return ResponseEntity.ok(transportService.getAllVehicles());
    }

    // @PreAuthorize("hasAuthority('VEHICLE_VIEW')")
    @GetMapping("/vehicles/{id}")
    public ResponseEntity<Vehicle> getVehicleById(@PathVariable Long id) {
        return ResponseEntity.ok(transportService.getVehicleById(id));
    }

    // @PreAuthorize("hasAuthority('VEHICLE_UPDATE')")
    @PutMapping("/vehicles/{id}")
    public ResponseEntity<Vehicle> updateVehicle(
            @PathVariable Long id,
            @RequestBody Vehicle vehicle) {
        return ResponseEntity.ok(transportService.updateVehicleById(id, vehicle));
    }

    // @PreAuthorize("hasAuthority('VEHICLE_UPDATE')")
    @PatchMapping("/vehicles/{id}")
    public ResponseEntity<Vehicle> patchVehicle(
            @PathVariable Long id,
            @RequestBody Vehicle vehicle) {
        return ResponseEntity.ok(transportService.patchVehicleById(id, vehicle));
    }

    // @PreAuthorize("hasAuthority('VEHICLE_DELETE')") // TEMPORARILY COMMENTED OUT
    @DeleteMapping("/vehicles/{id}")
    public ResponseEntity<String> deleteVehicle(@PathVariable Long id) {
        transportService.deleteVehicleById(id);
        return ResponseEntity.ok("Vehicle deleted successfully");
    }

    /*
     * @PreAuthorize("hasAuthority('VEHICLE_DELETE')")
     * 
     * @DeleteMapping("/vehicles/{vehicleNumber}") public ResponseEntity<String>
     * deleteVehicle(@PathVariable String vehicleNumber) {
     * transportService.deleteVehicle(vehicleNumber); return
     * ResponseEntity.ok("Vehicle deleted successfully"); }
     * 
     */ /*
         * =====================================================
         * ROUTEWAY
         * =====================================================
         */

    @PreAuthorize("hasAuthority('ROUTE_ADD')")
    @PostMapping("/routes")
    public ResponseEntity<RouteWay> addRoute(@RequestBody RouteWay routeWay) {
        return ResponseEntity.ok(transportService.addRoute(routeWay));
    }

    @PreAuthorize("hasAuthority('ROUTE_VIEW')")
    @GetMapping("/routes")
    public ResponseEntity<List<RouteWay>> getAllRoutes() {
        return ResponseEntity.ok(transportService.getAllRoutes());
    }

    @PreAuthorize("hasAuthority('ROUTE_VIEW')")
    @GetMapping("/routes/active")
    public ResponseEntity<List<RouteWay>> getActiveRoutes() {
        return ResponseEntity.ok(transportService.getActiveRoutes());
    }

    @PreAuthorize("hasAuthority('ROUTE_VIEW')")
    @GetMapping("/routes/{routeCode}")
    public ResponseEntity<RouteWay> getRouteByCode(@PathVariable Long routeCode) {
        return ResponseEntity.ok(transportService.getRouteByCode(routeCode));
    }

    @PreAuthorize("hasAuthority('ROUTE_UPDATE')")
    @PutMapping("/routes/{routeCode}")
    public ResponseEntity<RouteWay> updateRoute(
            @PathVariable Long routeCode,
            @RequestBody RouteWay routeWay) {
        return ResponseEntity.ok(transportService.updateRoute(routeCode, routeWay));
    }

    @PreAuthorize("hasAuthority('ROUTE_UPDATE')")
    @PatchMapping("/routes/{routeCode}")
    public ResponseEntity<RouteWay> patchRoute(
            @PathVariable Long routeCode,
            @RequestBody RouteWay routeWay) {
        return ResponseEntity.ok(transportService.patchRoute(routeCode, routeWay));
    }

    @PreAuthorize("hasAuthority('ROUTE_DELETE')")
    @DeleteMapping("/routes/{routeCode}")
    public ResponseEntity<String> deleteRoute(@PathVariable Long routeCode) {
        transportService.deleteRoute(routeCode);
        return ResponseEntity.ok("Route deleted successfully");
    }

    /*
     * =====================================================
     * DRIVER DETAILS
     * =====================================================
     */

    @PreAuthorize("hasAuthority('DRIVER_ADD')")
    @PostMapping("/drivers")
    public ResponseEntity<DriverDetails> addDriver(@RequestBody DriverDetails driver) {
        return ResponseEntity.ok(transportService.addDriver(driver));
    }

    @PreAuthorize("hasAuthority('DRIVER_VIEW')")
    @GetMapping("/drivers")
    public ResponseEntity<List<DriverDetails>> getAllDrivers() {
        return ResponseEntity.ok(transportService.getAllDrivers());
    }

    @PreAuthorize("hasAuthority('DRIVER_VIEW')")
    @GetMapping("/drivers/{driverId}")
    public ResponseEntity<DriverDetails> getDriverById(@PathVariable Long driverId) {
        return ResponseEntity.ok(transportService.getDriverById(driverId));
    }

    @PreAuthorize("hasAuthority('DRIVER_UPDATE')")
    @PutMapping("/drivers/{driverId}")
    public ResponseEntity<DriverDetails> updateDriver(
            @PathVariable Long driverId,
            @RequestBody DriverDetails driver) {
        return ResponseEntity.ok(transportService.updateDriver(driverId, driver));
    }

    @PreAuthorize("hasAuthority('DRIVER_UPDATE')")
    @PatchMapping("/drivers/{driverId}")
    public ResponseEntity<DriverDetails> patchDriver(
            @PathVariable Long driverId,
            @RequestBody DriverDetails driver) {

        return ResponseEntity.ok(
                transportService.patchDriver(driverId, driver));
    }

    @PreAuthorize("hasAuthority('DRIVER_DELETE')")
    @DeleteMapping("/drivers/{driverId}")
    public ResponseEntity<String> deleteDriver(@PathVariable Long driverId) {
        transportService.deleteDriver(driverId);
        return ResponseEntity.ok("Driver deleted successfully");
    }

    /*
     * ===================================================== *
     * CONDUCTOR DETAILS
     * =====================================================
     */

    @PreAuthorize("hasAuthority('CONDUCTOR_ADD')")
    @PostMapping("/conductors")
    public ResponseEntity<ConductorDetails> addConductor(
            @RequestBody ConductorDetails conductor) {

        return ResponseEntity.ok(
                transportService.addConductor(conductor));
    }

    @PreAuthorize("hasAuthority('CONDUCTOR_VIEW')")
    @GetMapping("/conductors")
    public ResponseEntity<List<ConductorDetails>> getAllConductors() {

        return ResponseEntity.ok(
                transportService.getAllConductors());
    }

    @PreAuthorize("hasAuthority('CONDUCTOR_VIEW')")
    @GetMapping("/conductors/active")
    public ResponseEntity<List<ConductorDetails>> getActiveConductors() {

        return ResponseEntity.ok(
                transportService.getActiveConductors());
    }

    @PreAuthorize("hasAuthority('CONDUCTOR_VIEW')")
    @GetMapping("/conductors/{conductorId}")
    public ResponseEntity<ConductorDetails> getConductorById(
            @PathVariable Long conductorId) {

        return ResponseEntity.ok(
                transportService.getConductorById(conductorId));
    }

    @PreAuthorize("hasAuthority('CONDUCTOR_UPDATE')")
    @PutMapping("/conductors/{conductorId}")
    public ResponseEntity<ConductorDetails> updateConductor(
            @PathVariable Long conductorId,
            @RequestBody ConductorDetails conductor) {

        return ResponseEntity.ok(
                transportService.updateConductor(conductorId, conductor));
    }

    @PreAuthorize("hasAuthority('CONDUCTOR_UPDATE')")
    @PatchMapping("/conductors/{conductorId}")
    public ResponseEntity<ConductorDetails> patchConductor(
            @PathVariable Long conductorId,
            @RequestBody ConductorDetails conductor) {

        return ResponseEntity.ok(
                transportService.patchConductor(conductorId, conductor));
    }

    @PreAuthorize("hasAuthority('CONDUCTOR_DELETE')")
    @DeleteMapping("/conductors/{conductorId}")
    public ResponseEntity<String> deleteConductor(
            @PathVariable Long conductorId) {

        transportService.deleteConductor(conductorId);
        return ResponseEntity.ok("Conductor deleted successfully");
    }

    /*
     * =====================================================
     * VEHICLE GPS
     * =====================================================
     */

    @PreAuthorize("hasAuthority('GPS_ADD')")
    @PostMapping("/gps")
    public ResponseEntity<VehicleGPS> saveLocation(@RequestBody VehicleGPS gps) {
        return ResponseEntity.ok(transportService.saveLocation(gps));
    }

    @PreAuthorize("hasAuthority('GPS_VIEW')")
    @GetMapping("/gps/latest/{vehicleId}")
    public ResponseEntity<VehicleGPS> getLatestLocation(
            @PathVariable Long vehicleId) {

        return ResponseEntity.ok(
                transportService.getLatestLocation(vehicleId));
    }

    /*
     * =====================================================
     * TRANSPORT ATTENDANCE
     * =====================================================
     */

    @PostMapping("/attendance/qr/mark")
    @PreAuthorize("hasAuthority('TRANSPORT_ATTENDANCE_VIEW')")
    public ResponseEntity<String> markAttendanceByQR(
            @RequestBody QRAttendanceRequest req) {
        transportService.markAttendanceByQR(req);
        return ResponseEntity.ok("Attendance marked successfully via QR");
    }

    @GetMapping("/attendance/qr")
    @PreAuthorize("hasAuthority('TRANSPORT_ATTENDANCE_VIEW')")
    public ResponseEntity<byte[]> generateQR(
            @RequestParam Long vehicleId,
            @RequestParam Long routeId,
            @RequestParam String session)
            throws Exception {
        String qrData = String.format(
                "{\"vehicleId\":%d,\"routeId\":%d,\"date\":\"%s\",\"session\":\"%s\"}",
                vehicleId,
                routeId,
                LocalDate.now(),
                session);

        byte[] qr = QRCodeUtil.generateQR(qrData);
        return ResponseEntity.ok()
                .header("Content-Type", "image/png")
                .body(qr);
    }

}