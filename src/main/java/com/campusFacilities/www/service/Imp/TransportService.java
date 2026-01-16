package com.campusFacilities.www.service.Imp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.campusFacilities.www.model.Transport.ConductorDetails;
import com.campusFacilities.www.model.Transport.DriverDetails;
import com.campusFacilities.www.model.Transport.RouteWay;
import com.campusFacilities.www.model.Transport.TransportAttendance;
import com.campusFacilities.www.model.Transport.Vehicle;
import com.campusFacilities.www.model.Transport.VehicleGPS;
import com.campusFacilities.www.repository.Transport.ConductorDetailsRepository;
import com.campusFacilities.www.repository.Transport.DriverDetailsRepository;
import com.campusFacilities.www.repository.Transport.RouteWayRepository;
import com.campusFacilities.www.repository.Transport.TransportAttendanceRepository;
import com.campusFacilities.www.repository.Transport.VehicleGPSRepository;
import com.campusFacilities.www.repository.Transport.VehicleRepository;


@Service
public class TransportService {

    @Autowired
    private VehicleRepository vehicleRepository;
    
   
    @Autowired
    private RouteWayRepository routeWayRepository;    
    
    @Autowired
    private  DriverDetailsRepository driverDetailsRepository;
    

    @Autowired
    private ConductorDetailsRepository conductorRepository;
    
    @Autowired
    private VehicleGPSRepository gpsRepository;

    @Autowired
    private TransportAttendanceRepository attendanceRepository;
    
    // ======================== Vehicle =========================//

    public Vehicle addVehicle(Vehicle vehicle) {

        RouteWay route = routeWayRepository
                .findById(vehicle.getRoute().getRouteCode())
                .orElseThrow(() -> new RuntimeException("Route not found"));
        vehicle.setRoute(route);
        
        if (vehicle.getOccupiedSeats() == null) {
            vehicle.setOccupiedSeats(0);
        }

        if (vehicle.getVehicleStatus() == null) {
            vehicle.setVehicleStatus(Vehicle.VehicleStatus.INACTIVE);
        }

        if (vehicle.getGpsEnabled() == null) {
            vehicle.setGpsEnabled(false);
        }

        return vehicleRepository.save(vehicle);
    }

    
    /* ================= GET ALL VEHICLES ================= */
    
    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    /* ================= GET VEHICLE BY NUMBER ================= */
    
    public Vehicle getVehicleByNumber(String vehicleNumber) {
        return vehicleRepository.findById(vehicleNumber)
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));
    }

    /* ================= UPDATE VEHICLE (PUT) ================= */
    
    public Vehicle updateVehicle(String vehicleNumber, Vehicle vehicle) {

        Vehicle existing = vehicleRepository.findById(vehicleNumber)
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));

        existing.setVehicletype(vehicle.getVehicletype());
        existing.setCapacity(vehicle.getCapacity());
        existing.setOccupiedSeats(vehicle.getOccupiedSeats());
        existing.setVehicleStatus(vehicle.getVehicleStatus());
        existing.setGpsEnabled(vehicle.getGpsEnabled());

        if (vehicle.getRoute() != null) {
            RouteWay route = routeWayRepository
                    .findById(vehicle.getRoute().getRouteCode())
                    .orElseThrow(() -> new RuntimeException("Route not found"));
            existing.setRoute(route);
        }

        return vehicleRepository.save(existing);
    }

    /* ================= PATCH VEHICLE ================= */
    
    public Vehicle patchVehicle(String vehicleNumber, Vehicle vehicle) {

        Vehicle existing = vehicleRepository.findById(vehicleNumber)
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));

        if (vehicle.getVehicletype() != null)
            existing.setVehicletype(vehicle.getVehicletype());

        if (vehicle.getCapacity() != null)
            existing.setCapacity(vehicle.getCapacity());

        if (vehicle.getOccupiedSeats() != null)
            existing.setOccupiedSeats(vehicle.getOccupiedSeats());

        if (vehicle.getVehicleStatus() != null)
            existing.setVehicleStatus(vehicle.getVehicleStatus());

        if (vehicle.getGpsEnabled() != null)
            existing.setGpsEnabled(vehicle.getGpsEnabled());

        if (vehicle.getRoute() != null && vehicle.getRoute().getRouteCode() != null) {
            RouteWay route = routeWayRepository
                    .findById(vehicle.getRoute().getRouteCode())
                    .orElseThrow(() -> new RuntimeException("Route not found"));
            existing.setRoute(route);
        }

        return vehicleRepository.save(existing);
    }

    /* ================= DELETE VEHICLE ================= */
    
    
    public void deleteVehicle(String vehicleNumber) {
        vehicleRepository.delete(vehicleNumber);
    }

    /* ================= BUSINESS LOGIC ================= */

    // Capacity full → auto INACTIVE
    public void updateCapacityStatus(String vehicleNumber) {
        Vehicle vehicle = getVehicleByNumber(vehicleNumber);

        if (vehicle.getOccupiedSeats() >= vehicle.getCapacity()) {
            vehicle.setVehicleStatus(Vehicle.VehicleStatus.INACTIVE);
            vehicleRepository.save(vehicle);
        }
    }

    // Accident → DEACTIVE
    public void markVehicleDeactive(String vehicleNumber) {
        Vehicle vehicle = getVehicleByNumber(vehicleNumber);
        vehicle.setVehicleStatus(Vehicle.VehicleStatus.DEACTIVE);
        vehicleRepository.save(vehicle);
    }

    // ================= ROUTEAY =================

    public RouteWay addRoute(RouteWay routeWay) {

        if (routeWay.getActive() == null) {
            routeWay.setActive(true);
        }

        if (routeWay.getAssigndVehicle() != null) {
            Vehicle vehicle = vehicleRepository
                    .findById(((Vehicle) routeWay.getAssigndVehicle()).getVechicleNumber())
                    .orElseThrow(() -> new RuntimeException("Vehicle not found"));
            routeWay.setAssigndVehicle(vehicle);
        }

        return routeWayRepository.save(routeWay);
    }

    /* ================= GET ALL ROUTES ================= */
    
    
    public List<RouteWay> getAllRoutes() {
        return routeWayRepository.findAll();
    }

    /* ================= GET ACTIVE ROUTES ================= */
    
    public List<RouteWay> getActiveRoutes() {
        return routeWayRepository.findByActiveTrue();
    }

    /* ================= GET ROUTE BY CODE ================= */
    
    public RouteWay getRouteByCode(Long routeCode) {
        return routeWayRepository.findById(routeCode)
                .orElseThrow(() -> new RuntimeException("Route not found"));
    }

    /* ================= UPDATE ROUTE (PUT) ================= */
    
    public RouteWay updateRoute(Long routeCode, RouteWay routeWay) {

        RouteWay existing = getRouteByCode(routeCode);

        existing.setRouteName(routeWay.getRouteName());
        existing.setPickupPoints(routeWay.getPickupPoints());
        existing.setDropPoints(routeWay.getDropPoints());
        existing.setDistanceKm(routeWay.getDistanceKm());
        existing.setEstimatedTimeMinutes(routeWay.getEstimatedTimeMinutes());
        existing.setMaxStudents(routeWay.getMaxStudents());
        existing.setActive(routeWay.getActive());

        if (routeWay.getAssigndVehicle() != null) {
            Vehicle vehicle = vehicleRepository
                    .findById(((Vehicle) routeWay.getAssigndVehicle()).getVechicleNumber())
                    .orElseThrow(() -> new RuntimeException("Vehicle not found"));
            existing.setAssigndVehicle(vehicle);
        }

        return routeWayRepository.save(existing);
    }

    /* ================= PATCH ROUTE ================= */
    
    
    public RouteWay patchRoute(Long routeCode, RouteWay routeWay) {

        RouteWay existing = getRouteByCode(routeCode);

        if (routeWay.getRouteName() != null)
            existing.setRouteName(routeWay.getRouteName());

        if (routeWay.getPickupPoints() != null)
            existing.setPickupPoints(routeWay.getPickupPoints());

        if (routeWay.getDropPoints() != null)
            existing.setDropPoints(routeWay.getDropPoints());

        if (routeWay.getDistanceKm() != null)
            existing.setDistanceKm(routeWay.getDistanceKm());

        if (routeWay.getEstimatedTimeMinutes() != null)
            existing.setEstimatedTimeMinutes(routeWay.getEstimatedTimeMinutes());

        if (routeWay.getMaxStudents() != null)
            existing.setMaxStudents(routeWay.getMaxStudents());

        if (routeWay.getActive() != null)
            existing.setActive(routeWay.getActive());

        if (routeWay.getAssigndVehicle() != null &&
            ((Vehicle) routeWay.getAssigndVehicle()).getVechicleNumber() != null) {

            Vehicle vehicle = vehicleRepository
                    .findById(((Vehicle) routeWay.getAssigndVehicle()).getVechicleNumber())
                    .orElseThrow(() -> new RuntimeException("Vehicle not found"));
            existing.setAssigndVehicle(vehicle);
        }

        return routeWayRepository.save(existing);
    }

    /* ================= DELETE ROUTE ================= */
    
    
    public void deleteRoute(Long routeCode) {
        routeWayRepository.deleteById(routeCode);
    }

    /* ================= BUSINESS LOGIC ================= */

    // Deactivate route (vehicle accident, maintenance, etc.)
    public void deactivateRoute(Long routeCode) {
        RouteWay route = getRouteByCode(routeCode);
        route.setActive(false);
        routeWayRepository.save(route);
    }

    // Assign vehicle to route
    public RouteWay assigningVehicle(Long routeCode, String vehicleNumber) {
        RouteWay route = getRouteByCode(routeCode);

        Vehicle vehicle = vehicleRepository.findOne(vehicleNumber)
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));

        route.setAssigndVehicle(vehicle);
        return routeWayRepository.save(route);
    }
    
    //=========================== DRIVER DETAILS===================//
    
    public DriverDetails addDriver(DriverDetails driver) {

        // License validity auto check
    	
        if (driver.getLicenseExpiryDate().isBefore(LocalDate.now())) {
            driver.setLicenseValidityStatus(
                    DriverDetails.LicenseValidityStatus.EXPIRED);
        } else {
            driver.setLicenseValidityStatus(
                    DriverDetails.LicenseValidityStatus.VALID);
        }

        driver.setVerificationStatus(
                DriverDetails.DriverStatus.PENDING);

        driver.setActive(true);

        // Assign vehicle if provided
        if (driver.getVehicle() != null) {
            Vehicle vehicle = vehicleRepository
                    .findById(driver.getVehicle().getVechicleNumber())
                    .orElseThrow(() -> new RuntimeException("Vehicle not found"));
            driver.setVehicle(vehicle);
        }

        // Assign route if provided
        if (driver.getRoute() != null) {
            RouteWay route = routeWayRepository
                    .findById(driver.getRoute().getRouteCode())
                    .orElseThrow(() -> new RuntimeException("Route not found"));
            driver.setRoute(route);
        }

        return driverDetailsRepository.save(driver);
    }

    /* ================= GET ALL DRIVERS ================= */
    
    public List<DriverDetails> getAllDrivers() {
        return driverDetailsRepository.findAll();
    }

    /* ================= GET ACTIVE DRIVERS ================= */
    
    public List<DriverDetails> getActiveDrivers() {
        return driverDetailsRepository.findByActiveTrue();
    }

    /* ================= GET DRIVER BY ID ================= */
    
    public DriverDetails getDriverById(Long driverId) {
        return driverDetailsRepository.findById(driverId)
                .orElseThrow(() -> new RuntimeException("Driver not found"));
    }

    /* ================= UPDATE DRIVER (PUT) ================= */
    public DriverDetails updateDriver(Long driverId, DriverDetails driver) {

        DriverDetails existing = getDriverById(driverId);

        existing.setName(driver.getName());
        existing.setContactNumber(driver.getContactNumber());
        existing.setExperienceYears(driver.getExperienceYears());
        existing.setRole(driver.getRole());
        existing.setShift(driver.getShift());
        existing.setExperienceCategory(driver.getExperienceCategory());
        existing.setBackgroundVerified(driver.getBackgroundVerified());
        existing.setVerificationStatus(driver.getVerificationStatus());
        existing.setLicenseExpiryDate(driver.getLicenseExpiryDate());

        return driverDetailsRepository.save(existing);
    }

    /* ================= PATCH DRIVER ================= */
    public DriverDetails patchDriver(Long driverId, DriverDetails driver) {

        DriverDetails existing = getDriverById(driverId);

        if (driver.getName() != null)
            existing.setName(driver.getName());

        if (driver.getContactNumber() != null)
            existing.setContactNumber(driver.getContactNumber());

        if (driver.getShift() != null)
            existing.setShift(driver.getShift());

        if (driver.getExperienceYears() != null)
            existing.setExperienceYears(driver.getExperienceYears());

        if (driver.getVerificationStatus() != null)
            existing.setVerificationStatus(driver.getVerificationStatus());

        if (driver.getActive() != null)
            existing.setActive(driver.getActive());

        return driverDetailsRepository.save(existing);
    }

    /* ================= ASSIGN VEHICLE ================= */
    
    public DriverDetails assignedVehicle(Long driverId, String vechicleNumber) {

        DriverDetails driver = getDriverById(driverId);

        Vehicle vehicle = vehicleRepository.findAll(vechicleNumber)
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));

        driver.setVehicle(vehicle);
        return driverDetailsRepository.save(driver);
    }

    /* ================= ASSIGN ROUTE ================= */
    public DriverDetails assigningRoute(Long driverId, Long routeCode) {

        DriverDetails driver = getDriverById(driverId);

        RouteWay route = routeWayRepository.findById(routeCode)
                .orElseThrow(() -> new RuntimeException("Route not found"));

        driver.setRoute(route);
        return driverDetailsRepository.save(driver);
    }

    /* ================= SUSPEND DRIVER ================= */
    
    public void suspendDriver(Long driverId) {
        DriverDetails driver = getDriverById(driverId);
        driver.setVerificationStatus(
                DriverDetails.DriverStatus.SUSPENDED);
        driver.setActive(false);
        driverDetailsRepository.save(driver);
    }

    /* ================= DELETE DRIVER ================= */
    public void deleteDriver(Long driverId) {
        driverDetailsRepository.deleteById(driverId);
    }
    
   //==================CONDUCTOR DETAILS=======================//
    
    
    
    /* ================= ADD CONDUCTOR ================= */
    public ConductorDetails addConductor(ConductorDetails conductor) {

        conductor.setVerificationStatus(
                ConductorDetails.ConductorVerificationStatus.PENDING);
        conductor.setActive(true);

        // Assign vehicle if provided
        if (conductor.getVehicle() != null) {
            Vehicle vehicle = vehicleRepository
                    .findById(conductor.getVehicle().getVechicleNumber())
                    .orElseThrow(() -> new RuntimeException("Vehicle not found"));
            conductor.setVehicle(vehicle);
        }

        // Assign route if provided
        if (conductor.getRoute() != null) {
            RouteWay route = routeWayRepository
                    .findById(conductor.getRoute().getRouteCode())
                    .orElseThrow(() -> new RuntimeException("Route not found"));
            conductor.setRoute(route);
        }

        return conductorRepository.save(conductor);
    }

    /* ================= GET ALL CONDUCTORS ================= */
    
    public List<ConductorDetails> getAllConductors() {
        return conductorRepository.findAll();
    }

    /* ================= GET ACTIVE CONDUCTORS ================= */
    
    public List<ConductorDetails> getActiveConductors() {
        return conductorRepository.findByActiveTrue();
    }

    /* ================= GET BY ID ================= */
    
    public ConductorDetails getConductorById(Long conductorId) {
        return conductorRepository.findById(conductorId)
                .orElseThrow(() -> new RuntimeException("Conductor not found"));
    }

    /* ================= UPDATE CONDUCTOR (PUT) ================= */
    
    public ConductorDetails updateConductor(
            Long conductorId,
            ConductorDetails conductor) {

        ConductorDetails existing = getConductorById(conductorId);

        existing.setName(conductor.getName());
        existing.setContactNumber(conductor.getContactNumber());
        existing.setExperienceYears(conductor.getExperienceYears());
        existing.setVerificationStatus(conductor.getVerificationStatus());
        existing.setActive(conductor.getActive());

        return conductorRepository.save(existing);
    }

    /* ================= PATCH CONDUCTOR ================= */
    
    public ConductorDetails patchConductor(
            Long conductorId,
            ConductorDetails conductor) {

        ConductorDetails existing = getConductorById(conductorId);

        if (conductor.getName() != null)
            existing.setName(conductor.getName());

        if (conductor.getContactNumber() != null)
            existing.setContactNumber(conductor.getContactNumber());

        if (conductor.getExperienceYears() != null)
            existing.setExperienceYears(conductor.getExperienceYears());

        if (conductor.getVerificationStatus() != null)
            existing.setVerificationStatus(conductor.getVerificationStatus());

        if (conductor.getActive() != null)
            existing.setActive(conductor.getActive());

        return conductorRepository.save(existing);
    }

    /* ================= ASSIGN VEHICLE ================= */
    
    public ConductorDetails assignVehicle(
            Long conductorId,
            String vechicleNumber) {

        ConductorDetails conductor = getConductorById(conductorId);

        Vehicle vehicle = vehicleRepository.findById(vechicleNumber)
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));

        conductor.setVehicle(vehicle);
        return conductorRepository.save(conductor);
    }

    /* ================= ASSIGN ROUTE ================= */
    public ConductorDetails assignRoute(
            Long conductorId,
            Long routeCode) {

        ConductorDetails conductor = getConductorById(conductorId);

        RouteWay route = routeWayRepository.findById(routeCode)
                .orElseThrow(() -> new RuntimeException("Route not found"));

        conductor.setRoute(route);
        return conductorRepository.save(conductor);
    }

    /* ================= SUSPEND CONDUCTOR ================= */
    public void suspendConductor(Long conductorId) {
        ConductorDetails conductor = getConductorById(conductorId);
        conductor.setVerificationStatus(
                ConductorDetails.ConductorVerificationStatus.SUSPENDED);
        conductor.setActive(false);
        conductorRepository.save(conductor);
    }

    /* ================= DELETE CONDUCTOR ================= */
    public void deleteConductor(Long conductorId) {
        conductorRepository.deleteById(conductorId);
    }

    
    //================BUS GPS==========================
   
    /* ================= SAVE GPS LOCATION ================= */
    
    public VehicleGPS saveLocation(VehicleGPS gps) {

        gps.setTimestamp(LocalDateTime.now());

        // Auto status logic
        
        if (gps.getSpeed() == 0) {
            gps.setStatus("STOPPED");
        } else if (gps.getSpeed() > 0) {
            gps.setStatus("MOVING");
        }

        return gpsRepository.save(gps);
    }

    /* ================= GET LATEST LOCATION ================= */
    
    public VehicleGPS getLatestLocation(Long busId) {
        return gpsRepository
                .findTopByBusIdOrderByTimestampDesc(busId);
    }

    /* ================= GET LOCATION HISTORY ================= */
    
    public List<VehicleGPS> getLocationHistory(Long busId) {
        return gpsRepository
                .findByBusIdOrderByTimestampDesc(busId);
    }

    /* ================= DELETE GPS DATA (ADMIN) ================= */
    
    public void deleteGpsData(Long id) {
        gpsRepository.deleteById(id);
    }
    
    //=========================TRANSPORT ATTENDENCE====================//
    
    /* ================= MARK ATTENDANCE ================= */
    public TransportAttendance markAttendance(TransportAttendance attendance) {

        // One record per student per day
        attendanceRepository
                .findByStudentIdAndAttendanceDate(
                        attendance.getStudentId(),
                        attendance.getAttendanceDate())
                .ifPresent(existing -> {
                    throw new RuntimeException(
                            "Attendance already marked for this student today");
                });

        return attendanceRepository.save(attendance);
    }

    /* ================= MARK PICKUP ================= */
    public TransportAttendance markPickup(
            Long studentId,
            LocalDate date,
            TransportAttendance.TransportAttendanceStatus status,
            TransportAttendance.MarkedBy markedBy) {

        TransportAttendance attendance =
                attendanceRepository
                        .findByStudentIdAndAttendanceDate(studentId, date)
                        .orElseThrow(() ->
                                new RuntimeException("Attendance record not found"));

        attendance.setPickupStatus(status);
        attendance.setMarkedBy(markedBy);

        return attendanceRepository.save(attendance);
    }

    /* ================= MARK DROP ================= */
    public TransportAttendance markDrop(
            Long studentId,
            LocalDate date,
            TransportAttendance.TransportAttendanceStatus status,
            TransportAttendance.MarkedBy markedBy) {

        TransportAttendance attendance =
                attendanceRepository
                        .findByStudentIdAndAttendanceDate(studentId, date)
                        .orElseThrow(() ->
                                new RuntimeException("Attendance record not found"));

        attendance.setDropStatus(status);
        attendance.setMarkedBy(markedBy);

        return attendanceRepository.save(attendance);
    }

    /* ================= DAILY BUS REPORT ================= */
    
    public List<TransportAttendance> getBusAttendance(
            Long busId, LocalDate date) {

        return attendanceRepository
                .findByBusIdAndAttendanceDate(busId, date);
    }

    /* ================= ROUTE REPORT ================= */
    
    public List<TransportAttendance> getRouteAttendance(
            Long routeId, LocalDate date) {

        return attendanceRepository
                .findByRouteIdAndAttendanceDate(routeId, date);
    }

    /* ================= STUDENT HISTORY ================= */
    
    public TransportAttendance getStudentAttendance(
            Long studentId, LocalDate date) {

        return attendanceRepository
                .findByStudentIdAndAttendanceDate(studentId, date)
                .orElseThrow(() ->
                        new RuntimeException("No attendance found"));
    }
}
