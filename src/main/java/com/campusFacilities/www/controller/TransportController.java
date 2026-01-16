package com.campusFacilities.www.controller;
import java.time.LocalDate;
import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.campusFacilities.www.model.Transport.ConductorDetails;
import com.campusFacilities.www.model.Transport.DriverDetails;
import com.campusFacilities.www.model.Transport.RouteWay;
import com.campusFacilities.www.model.Transport.TransportAttendance;
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

        /* =====================================================
         * VEHICLE
         * ===================================================== */

        @PostMapping("/vehicles")
        public ResponseEntity<Vehicle> addVehicle(@RequestBody Vehicle vehicle) {
            return ResponseEntity.ok(transportService.addVehicle(vehicle));
        }

        @GetMapping("/vehicles")
        public ResponseEntity<List<Vehicle>> getAllVehicles() {
            return ResponseEntity.ok(transportService.getAllVehicles());
        }

        @GetMapping("/vehicles/{vehicleNumber}")
        public ResponseEntity<Vehicle> getVehicleByNumber(@PathVariable String vehicleNumber) {
            return ResponseEntity.ok(transportService.getVehicleByNumber(vehicleNumber));
        }

        @PutMapping("/vehicles/{vehicleNumber}")
        public ResponseEntity<Vehicle> updateVehicle(
                @PathVariable String vehicleNumber,
                @RequestBody Vehicle vehicle) {
            return ResponseEntity.ok(
                    transportService.updateVehicle(vehicleNumber, vehicle));
        }

        @PatchMapping("/vehicles/{vehicleNumber}")
        public ResponseEntity<Vehicle> patchVehicle(
                @PathVariable String vehicleNumber,
                @RequestBody Vehicle vehicle) {
            return ResponseEntity.ok(
                    transportService.patchVehicle(vehicleNumber, vehicle));
        }

        @DeleteMapping("/vehicles/{vehicleNumber}")
        public ResponseEntity<String> deleteVehicle(@PathVariable String vehicleNumber) {
            transportService.deleteVehicle(vehicleNumber);
            return ResponseEntity.ok("Vehicle deleted successfully");
        }

        /* =====================================================
                                 ROUTEWAY
         * ===================================================== */

        @PostMapping("/routes")
        public ResponseEntity<RouteWay> addRoute(@RequestBody RouteWay routeWay) {
            return ResponseEntity.ok(transportService.addRoute(routeWay));
        }

        @GetMapping("/routes")
        public ResponseEntity<List<RouteWay>> getAllRoutes() {
            return ResponseEntity.ok(transportService.getAllRoutes());
        }

        @GetMapping("/routes/active")
        public ResponseEntity<List<RouteWay>> getActiveRoutes() {
            return ResponseEntity.ok(transportService.getActiveRoutes());
        }

        @GetMapping("/routes/{routeCode}")
        public ResponseEntity<RouteWay> getRouteByCode(@PathVariable Long routeCode) {
            return ResponseEntity.ok(transportService.getRouteByCode(routeCode));
        }

        @PutMapping("/routes/{routeCode}")
        public ResponseEntity<RouteWay> updateRoute(
                @PathVariable Long routeCode,
                @RequestBody RouteWay routeWay) {
            return ResponseEntity.ok(
                    transportService.updateRoute(routeCode, routeWay));
        }

        @PatchMapping("/routes/{routeCode}")
        public ResponseEntity<RouteWay> patchRoute(
                @PathVariable Long routeCode,
                @RequestBody RouteWay routeWay) {
            return ResponseEntity.ok(
                    transportService.patchRoute(routeCode, routeWay));
        }

        @DeleteMapping("/routes/{routeCode}")
        public ResponseEntity<String> deleteRoute(@PathVariable Long routeCode) {
            transportService.deleteRoute(routeCode);
            return ResponseEntity.ok("Route deleted successfully");
        }

        /* =====================================================
                            DRIVER DETAILS
         * ===================================================== */

        @PostMapping("/drivers")
        public ResponseEntity<DriverDetails> addDriver(@RequestBody DriverDetails driver) {
            return ResponseEntity.ok(transportService.addDriver(driver));
        }

        @GetMapping("/drivers")
        public ResponseEntity<List<DriverDetails>> getAllDrivers() {
            return ResponseEntity.ok(transportService.getAllDrivers());
        }

        @GetMapping("/drivers/active")
        public ResponseEntity<List<DriverDetails>> getActiveDrivers() {
            return ResponseEntity.ok(transportService.getActiveDrivers());
        }

        @GetMapping("/drivers/{driverId}")
        public ResponseEntity<DriverDetails> getDriverById(@PathVariable Long driverId) {
            return ResponseEntity.ok(transportService.getDriverById(driverId));
        }

        @PutMapping("/drivers/{driverId}")
        public ResponseEntity<DriverDetails> updateDriver(
                @PathVariable Long driverId,
                @RequestBody DriverDetails driver) {
            return ResponseEntity.ok(
                    transportService.updateDriver(driverId, driver));
        }

        @PatchMapping("/drivers/{driverId}")
        public ResponseEntity<DriverDetails> patchDriver(
                @PathVariable Long driverId,
                @RequestBody DriverDetails driver) {
            return ResponseEntity.ok(
                    transportService.patchDriver(driverId, driver));
        }

        @DeleteMapping("/drivers/{driverId}")
        public ResponseEntity<String> deleteDriver(@PathVariable Long driverId) {
            transportService.deleteDriver(driverId);
            return ResponseEntity.ok("Driver deleted successfully");
        }

        /* =====================================================
                          CONDUCTOR DETAILS
         * ===================================================== */

        @PostMapping("/conductors")
        public ResponseEntity<ConductorDetails> addConductor(
                @RequestBody ConductorDetails conductor) {
            return ResponseEntity.ok(transportService.addConductor(conductor));
        }

        @GetMapping("/conductors")
        public ResponseEntity<List<ConductorDetails>> getAllConductors() {
            return ResponseEntity.ok(transportService.getAllConductors());
        }

        @GetMapping("/conductors/active")
        public ResponseEntity<List<ConductorDetails>> getActiveConductors() {
            return ResponseEntity.ok(transportService.getActiveConductors());
        }

        @GetMapping("/conductors/{conductorId}")
        public ResponseEntity<ConductorDetails> getConductorById(
                @PathVariable Long conductorId) {
            return ResponseEntity.ok(
                    transportService.getConductorById(conductorId));
        }

        @PutMapping("/conductors/{conductorId}")
        public ResponseEntity<ConductorDetails> updateConductor(
                @PathVariable Long conductorId,
                @RequestBody ConductorDetails conductor) {
            return ResponseEntity.ok(
                    transportService.updateConductor(conductorId, conductor));
        }

        @PatchMapping("/conductors/{conductorId}")
        public ResponseEntity<ConductorDetails> patchConductor(
                @PathVariable Long conductorId,
                @RequestBody ConductorDetails conductor) {
            return ResponseEntity.ok(
                    transportService.patchConductor(conductorId, conductor));
        }

        @DeleteMapping("/conductors/{conductorId}")
        public ResponseEntity<String> deleteConductor(
                @PathVariable Long conductorId) {
            transportService.deleteConductor(conductorId);
            return ResponseEntity.ok("Conductor deleted successfully");
        }

        /* =====================================================
                         VEHICLE GPS
         * ===================================================== */

        @PostMapping("/gps")
        public ResponseEntity<VehicleGPS> saveLocation(
                @RequestBody VehicleGPS gps) {
            return ResponseEntity.ok(transportService.saveLocation(gps));
        }

        @GetMapping("/gps/latest/{busId}")
        public ResponseEntity<VehicleGPS> getLatestLocation(
                @PathVariable Long busId) {
            return ResponseEntity.ok(
                    transportService.getLatestLocation(busId));
        }

        @GetMapping("/gps/history/{busId}")
        public ResponseEntity<List<VehicleGPS>> getLocationHistory(
                @PathVariable Long busId) {
            return ResponseEntity.ok(
                    transportService.getLocationHistory(busId));
        }

        /* =====================================================
                           TRANSPORT ATTENDANCE
         * ===================================================== */

        @PostMapping("/attendance")
        public ResponseEntity<TransportAttendance> markAttendance(
                @RequestBody TransportAttendance attendance) {
            return ResponseEntity.ok(
                    transportService.markAttendance(attendance));
        }

        @PostMapping("/attendance/pickup")
        public ResponseEntity<TransportAttendance> markPickup(
                @RequestParam Long studentId,
                @RequestParam LocalDate date,
                @RequestParam TransportAttendance.TransportAttendanceStatus status,
                @RequestParam TransportAttendance.MarkedBy markedBy) {

            return ResponseEntity.ok(
                    transportService.markPickup(studentId, date, status, markedBy));
        }

        @PostMapping("/attendance/drop")
        public ResponseEntity<TransportAttendance> markDrop(
                @RequestParam Long studentId,
                @RequestParam LocalDate date,
                @RequestParam TransportAttendance.TransportAttendanceStatus status,
                @RequestParam TransportAttendance.MarkedBy markedBy) {

            return ResponseEntity.ok(
                    transportService.markDrop(studentId, date, status, markedBy));
        }

        @GetMapping("/attendance/bus")
        public ResponseEntity<List<TransportAttendance>> getBusAttendance(
                @RequestParam Long busId,
                @RequestParam LocalDate date) {

            return ResponseEntity.ok(
                    transportService.getBusAttendance(busId, date));
        }

        @GetMapping("/attendance/route")
        public ResponseEntity<List<TransportAttendance>> getRouteAttendance(
                @RequestParam Long routeId,
                @RequestParam LocalDate date) {

            return ResponseEntity.ok(
                    transportService.getRouteAttendance(routeId, date));
        }
    }
