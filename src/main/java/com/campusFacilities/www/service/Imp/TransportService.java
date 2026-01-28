package com.campusFacilities.www.service.Imp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.campusFacilities.www.Transport.util.QRCodeUtil;
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
    
    /* ================= VEHICLE ===================== */

    public Vehicle addVehicle(Vehicle vehicle) {

        if (vehicle == null) 
        {
            throw new IllegalArgumentException("Vehicle must not be null");
        }

        if (vehicle.getRoute() != null && vehicle.getRoute().getRouteCode() != null) {
            RouteWay route = routeWayRepository
                    .findByRouteCode(vehicle.getRoute().getRouteCode())
                    .orElseThrow(() -> new RuntimeException("Route not found"));
            vehicle.setRoute(route);
        } else {
            vehicle.setRoute(null);
        }
        if (vehicle.getOccupiedSeats() == null) {
            vehicle.setOccupiedSeats(0);
        }

        vehicle.setVehicleStatus(
                vehicle.getVehicleStatus() == null
                        ? Vehicle.VehicleStatus.INACTIVE
                        : vehicle.getVehicleStatus());

        if (vehicle.getGpsEnabled() == null) {
            vehicle.setGpsEnabled(false);
        }

        return vehicleRepository.save(vehicle);
    }

    
    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    public Vehicle getVehicleByNumber(String vehicleNumber) {
        return vehicleRepository.findByVehicleNumber(vehicleNumber)
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));
    }

    public Vehicle updateVehicle(String vehicleNumber, Vehicle vehicle) {

        Vehicle existing = getVehicleByNumber(vehicleNumber);

        existing.setVehicletype(vehicle.getVehicletype());
        existing.setCapacity(vehicle.getCapacity());
        existing.setOccupiedSeats(vehicle.getOccupiedSeats());
        existing.setVehicleStatus(vehicle.getVehicleStatus());
        existing.setGpsEnabled(vehicle.getGpsEnabled());

        if (vehicle.getRoute() != null) {
            RouteWay route = routeWayRepository
                    .findByRouteCode(vehicle.getRoute().getRouteCode())
                    .orElseThrow(() -> new RuntimeException("Route not found"));

            existing.setRoute(route);
        }


        return vehicleRepository.save(existing);
    }

    public Vehicle patchVehicle(String vehicleNumber, Vehicle vehicle) {

        Vehicle existing = getVehicleByNumber(vehicleNumber);

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

        return vehicleRepository.save(existing);
    }

    
	  public void deleteVehicle(String vehicleNumber) {
	  vehicleRepository.delete(getVehicleByNumber(vehicleNumber)); 
	  }
	 
     //================================ ROUTEWAY ======================================== //

    public RouteWay addRoute(RouteWay routeWay) {
        if (routeWay.getActive() == null) {
            routeWay.setActive(true);
        }
        return routeWayRepository.save(routeWay);
    }

    public List<RouteWay> getAllRoutes() {
        return routeWayRepository.findAll();
    }

    public List<RouteWay> getActiveRoutes() {
        return routeWayRepository.findByActiveTrue();
    }

    public RouteWay getRouteByCode(Long routeCode) {
        return routeWayRepository.findByRouteCode(routeCode)
                .orElseThrow(() -> new RuntimeException("Route not found"));
    }

    public RouteWay updateRoute(Long routeCode, RouteWay routeWay) {

        RouteWay existing = getRouteByCode(routeCode);

        existing.setRouteName(routeWay.getRouteName());
        existing.setPickupPoints(routeWay.getPickupPoints());
        existing.setDropPoints(routeWay.getDropPoints());
        existing.setDistanceKm(routeWay.getDistanceKm());
        existing.setEstimatedTimeMinutes(routeWay.getEstimatedTimeMinutes());
        existing.setActive(routeWay.getActive());

        return routeWayRepository.save(existing);
    }

    public RouteWay patchRoute(Long routeCode, RouteWay routeWay) {

        RouteWay existing = getRouteByCode(routeCode);

        if (routeWay.getRouteName() != null)
            existing.setRouteName(routeWay.getRouteName());
        if (routeWay.getActive() != null)
            existing.setActive(routeWay.getActive());

        return routeWayRepository.save(existing);
    }

    public void deleteRoute(Long routeCode) {
        routeWayRepository.delete(getRouteByCode(routeCode));
    }

    /* ============================== DRIVER DETAILS ========================================== */

    public DriverDetails addDriver(DriverDetails driver) {
        driver.setActive(true);
        return driverDetailsRepository.save(driver);
    }

    public List<DriverDetails> getAllDrivers() {
        return driverDetailsRepository.findAll();
    }

    public List<DriverDetails> getActiveDrivers() {
        return driverDetailsRepository.findByActiveTrue();
    }

    public DriverDetails getDriverById(Long driverId) {
        return driverDetailsRepository.findById(driverId)
                .orElseThrow(() -> new RuntimeException("Driver not found"));
    }

    public DriverDetails updateDriver(Long driverId, DriverDetails driver) 
    
    {
        DriverDetails existing = getDriverById(driverId);
        existing.setFullName(driver.getFullName());
        existing.setContactNumber(driver.getContactNumber());
        return driverDetailsRepository.save(existing);
    }
    
    public DriverDetails patchDriver(Long driverId, DriverDetails updates) {

        DriverDetails existing = driverDetailsRepository.findById(driverId)
                .orElseThrow(() -> new RuntimeException("Driver not found"));

        if (updates.getFullName() != null)
            existing.setFullName(updates.getFullName());

        if (updates.getContactNumber() != null)
            existing.setContactNumber(updates.getContactNumber());

        if (updates.getLicenseNumber() != null)
            existing.setLicenseNumber(updates.getLicenseNumber());

        if (updates.getLicenseExpiryDate() != null)
            existing.setLicenseExpiryDate(updates.getLicenseExpiryDate());

        if (updates.getRole() != null)
            existing.setRole(updates.getRole());

        if (updates.getExperienceCategory() != null)
            existing.setExperienceCategory(updates.getExperienceCategory());

        if (updates.getExperienceYears() != null)
            existing.setExperienceYears(updates.getExperienceYears());

        if (updates.getShift() != null)
            existing.setShift(updates.getShift());

        if (updates.getBackgroundVerified() != null)
            existing.setBackgroundVerified(updates.getBackgroundVerified());

        if (updates.getLicenseValidityStatus() != null)
            existing.setLicenseValidityStatus(updates.getLicenseValidityStatus());

        if (updates.getVerificationStatus() != null)
            existing.setVerificationStatus(updates.getVerificationStatus());

        if (updates.getActive() != null)
            existing.setActive(updates.getActive());

        if (updates.getVehicle() != null && updates.getVehicle().getId() != null)
            existing.setVehicle(
                vehicleRepository.findById(updates.getVehicle().getId())
                    .orElseThrow(() -> new RuntimeException("Vehicle not found"))
            );

        if (updates.getRoute() != null && updates.getRoute().getId() != null)
            existing.setRoute(
                routeWayRepository.findById(updates.getRoute().getId())
                    .orElseThrow(() -> new RuntimeException("Route not found"))
            );

        return driverDetailsRepository.save(existing);
    }


    public void deleteDriver(Long driverId) {
        driverDetailsRepository.deleteById(driverId);
    }
  
    /* ================================== CONDUCTOR DETAILS ======================================== */

    public ConductorDetails addConductor(ConductorDetails conductor) {
        conductor.setActive(true);
        return conductorRepository.save(conductor);
    }

    public List<ConductorDetails> getAllConductors() {
        return conductorRepository.findAll();
    }

    public List<ConductorDetails> getActiveConductors() {
        return conductorRepository.findByActiveTrue();
    }

    public ConductorDetails getConductorById(Long conductorId) {
        return conductorRepository.findById(conductorId)
                .orElseThrow(() -> new RuntimeException("Conductor not found"));
    }

    public ConductorDetails updateConductor(Long conductorId, ConductorDetails conductor) {

        ConductorDetails existing = getConductorById(conductorId);

        existing.setConductorName(conductor.getConductorName());
        existing.setContactNumber(conductor.getContactNumber());
        existing.setExperienceYears(conductor.getExperienceYears());
        existing.setVerificationStatus(conductor.getVerificationStatus());
        existing.setActive(conductor.getActive());

        if (conductor.getRoute() != null && conductor.getRoute().getId() != null) {
            RouteWay route = routeWayRepository.findById(conductor.getRoute().getId())
                    .orElseThrow(() -> new RuntimeException("Route not found"));
            existing.setRoute(route);
        }

        if (conductor.getVehicle() != null && conductor.getVehicle().getId() != null) {
            Vehicle vehicle = vehicleRepository.findById(conductor.getVehicle().getId())
                    .orElseThrow(() -> new RuntimeException("Vehicle not found"));
            existing.setVehicle(vehicle);
        }

        return conductorRepository.save(existing);
    }
    public ConductorDetails patchConductor(Long conductorId, ConductorDetails conductor) {

        ConductorDetails existing = getConductorById(conductorId);

        if (conductor.getConductorName() != null)
            existing.setConductorName(conductor.getConductorName());

        if (conductor.getContactNumber() != null)
            existing.setContactNumber(conductor.getContactNumber());

        if (conductor.getExperienceYears() != null)
            existing.setExperienceYears(conductor.getExperienceYears());

        if (conductor.getVerificationStatus() != null)
            existing.setVerificationStatus(conductor.getVerificationStatus());

        if (conductor.getActive() != null)
            existing.setActive(conductor.getActive());

        if (conductor.getRoute() != null && conductor.getRoute().getId() != null) {
            RouteWay route = routeWayRepository.findById(conductor.getRoute().getId())
                    .orElseThrow(() -> new RuntimeException("Route not found"));
            existing.setRoute(route);
        }

        if (conductor.getVehicle() != null && conductor.getVehicle().getId() != null) {
            Vehicle vehicle = vehicleRepository.findById(conductor.getVehicle().getId())
                    .orElseThrow(() -> new RuntimeException("Vehicle not found"));
            existing.setVehicle(vehicle);
        }

        return conductorRepository.save(existing);
    }


    public void deleteConductor(Long conductorId) {
        conductorRepository.deleteById(conductorId);
    }

    //================================== VEHICLE GPS =================================== */

    public VehicleGPS saveLocation(VehicleGPS gps) {
        gps.setTimestamp(LocalDateTime.now());
        return gpsRepository.save(gps);
    }

    public VehicleGPS getLatestLocation(Long vehicleId) {
        return gpsRepository
                .findTopByVehicle_IdOrderByTimestampDesc(vehicleId)
                .orElse(null);
    }
   
    /* ============================== TRANSPORT ATTENDANCE ===================================== */

    
    // 🔹 Generate Daily QR for Vehicle
    public byte[] generateVehicleQR(Long vehicleId, String type) {

        String qrText =
                "vehicleId=" + vehicleId +
                "|date=" + LocalDate.now() +
                "|type=" + type;

        return QRCodeUtil.generateQRCode(qrText);
    }

    // 🔹 Scan QR and Mark Attendance
    public TransportAttendance markAttendance(Long studentId, String qrText) {

        Map<String, String> qrData = parseQRText(qrText);

        Long vehicleId = Long.parseLong(qrData.get("vehicleId"));
        LocalDate date = LocalDate.parse(qrData.get("date"));
        String type = qrData.get("type");

        Vehicle vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));

        TransportAttendance attendance =
                attendanceRepository
                        .findByStudentIdAndVehicleAndAttendanceDate(
                                studentId, vehicle, date
                        )
                        .orElse(new TransportAttendance());

        attendance.setStudentId(studentId);
        attendance.setVehicle(vehicle);
        attendance.setAttendanceDate(date);
        attendance.setMarkedBy(TransportAttendance.MarkedBy.QRCODE);

        if ("PICKUP".equalsIgnoreCase(type)) {
            attendance.setPickupStatus(
                    TransportAttendance.TransportAttendanceStatus.PICKED_UP);
        } else if ("DROP".equalsIgnoreCase(type)) {
            attendance.setDropStatus(
                    TransportAttendance.TransportAttendanceStatus.DROPPED);
        }

        return attendanceRepository.save(attendance);
    }

    // 🔹 QR Text Parser
    
    private Map<String, String> parseQRText(String qrText) {
        Map<String, String> map = new HashMap<>();
        String[] parts = qrText.split("\\|");

        for (String part : parts) {
            String[] keyValue = part.split("=");
            map.put(keyValue[0], keyValue[1]);
        }
        return map;
    }


	public @Nullable Object markAttendanceFromToken(String qrText) {
		
		return null;
	}
}