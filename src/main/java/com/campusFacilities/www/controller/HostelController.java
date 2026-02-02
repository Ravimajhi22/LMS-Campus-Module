package com.campusFacilities.www.controller;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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

import com.campusFacilities.www.model.Hostel.Hostel;
import com.campusFacilities.www.model.Hostel.HostelAttendance;
import com.campusFacilities.www.model.Hostel.HostelComplaint;
import com.campusFacilities.www.model.Hostel.HostelRoom;
import com.campusFacilities.www.model.Hostel.MessDayMenu;
import com.campusFacilities.www.model.Hostel.StudentHealthIncident;
import com.campusFacilities.www.model.Hostel.StudentHostelAllocation;
import com.campusFacilities.www.model.Hostel.StudentVisitEntry;
import com.campusFacilities.www.service.Imp.HostelServiceImpl;

@RestController
@RequestMapping("/campus")
public class HostelController {

    @Autowired
    private HostelServiceImpl hostelService;

    // ============================ HOSTEL =======================//
   
    //  CREATE
    @PreAuthorize("hasAuthority('HOSTEL_CREATE')")
    @PostMapping("/hostel")
    public ResponseEntity<Hostel> createHostel(@RequestBody Hostel hostel) {
        return ResponseEntity.ok(hostelService.createHostel(hostel));
    }

    // GET ALL
    @PreAuthorize("hasAuthority('HOSTEL_VIEW')")
    @GetMapping("/hostels")
    public ResponseEntity<List<Hostel>> getAllHostels() {
        return ResponseEntity.ok(hostelService.getAllHostels());
    }

    // GET BY ID
    @PreAuthorize("hasAuthority('HOSTEL_VIEW')")
    @GetMapping("/hostels/{id}")
    public ResponseEntity<Hostel> getHostelById(@PathVariable Long id) {
        return ResponseEntity.ok(hostelService.getHostelById(id));
    }
    
    //PUT Method
    @PreAuthorize("hasAuthority('HOSTEL_UPDATE')")
    @PutMapping("/hostel/{id}")
    public ResponseEntity<Hostel> updateHostel(
            @PathVariable Long id,
            @RequestBody Hostel hostel) {
        return ResponseEntity.ok(hostelService.updateHostel(id, hostel));
    }


    // PATCH (PARTIAL UPDATE)
    @PreAuthorize("hasAuthority('HOSTEL_UPDATE')")
    @PatchMapping("hostel/{id}")
    public ResponseEntity<Hostel> updateHostelPartial(
            @PathVariable Long id,
            @RequestBody Hostel hostel) {
        return ResponseEntity.ok(hostelService.updateHostelPartial(id, hostel));
    }

    // DELETE (SOFT DELETE)
    @PreAuthorize("hasAuthority('HOSTEL_DELETE')")
    @DeleteMapping("hostel/{id}")
    public ResponseEntity<String> deleteHostel(@PathVariable Long id) {
        hostelService.deleteHostel(id);
        return ResponseEntity.ok("Hostel deleted successfully");
    }

    // ================= Hostel ROOM ====================//
        
    // CREATE ROOM
    @PreAuthorize("hasAuthority('HOSTEL_ROOM_CREATE')")
    @PostMapping("/room")
    public ResponseEntity<HostelRoom> createRoom(@RequestBody HostelRoom room) {
        return ResponseEntity.ok(hostelService.createRoom(room));
    }

    // GET ALL ROOMS
    @PreAuthorize("hasAuthority('HOSTEL_ROOM_VIEW')")
    @GetMapping("/rooms")
    public ResponseEntity<List<HostelRoom>> getAllRooms() {
        return ResponseEntity.ok(hostelService.getAllRooms());
    }

    // GET ROOM BY ID
    @PreAuthorize("hasAuthority('HOSTEL_ROOM_VIEW')")
    @GetMapping("/rooms/{id}")
    public ResponseEntity<HostelRoom> getRoomById(@PathVariable Long id) {
        return ResponseEntity.ok(hostelService.getRoomById(id));
    }

    // PATCH (PARTIAL UPDATE)
    @PreAuthorize("hasAuthority('HOSTEL_ROOM_UPDATE')")
    @PatchMapping("rooms/{id}")
    public ResponseEntity<HostelRoom> updateRoomPartial(
            @PathVariable Long id,
            @RequestBody HostelRoom room) {
        return ResponseEntity.ok(hostelService.updateRoomPartial(id, room));
    }
 // PUT (FULL UPDATE)
    @PreAuthorize("hasAuthority('HOSTEL_ROOM_UPDATE')")
    @PutMapping("/rooms/{id}")
    public ResponseEntity<HostelRoom> updateRoom(
            @PathVariable Long id,
            @RequestBody HostelRoom room) {

        return ResponseEntity.ok(hostelService.updateRoom(id, room));
    }


    // PATCH STATUS
    @PreAuthorize("hasAuthority('HOSTEL_ROOM_STATUS_UPDATE')")
    @PatchMapping("/rooms/{id}/status")
    public ResponseEntity<HostelRoom> updateRoomStatus(
            @PathVariable Long id,
            @RequestParam HostelRoom.RoomStatus status) {
        return ResponseEntity.ok(hostelService.updateRoomStatus(id, status));
    }

    //DELETE (SOFT DELETE)
    @PreAuthorize("hasAuthority('HOSTEL_ROOM_DELETE')")
    @DeleteMapping("/rooms/{id}")
    public ResponseEntity<String> deleteRoom(@PathVariable Long id) {
        hostelService.deleteRoom(id);
        return ResponseEntity.ok("Room deleted successfully");
    }
    
    //===========================Hostel Attendance===========================//

    // MARK ATTENDANCE
    @PreAuthorize("hasAuthority('HOSTEL_ATTENDANCE_MARK')")
    @PostMapping("/attendance")
    public ResponseEntity<HostelAttendance> markAttendance(
            @RequestBody HostelAttendance attendance) {
        return ResponseEntity.ok(hostelService.markAttendance(attendance));
    }

    //  GET ALL ATTENDANCE (DATE FILTER OPTIONAL)
    @PreAuthorize("hasAuthority('HOSTEL_ATTENDANCE_VIEW')")
    @GetMapping("/attendances")
    public ResponseEntity<List<HostelAttendance>> getAllAttendance(
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate date) {
        return ResponseEntity.ok(hostelService.getAllAttendance(date));
    }

    //  GET BY ID
    @PreAuthorize("hasAuthority('HOSTEL_ATTENDANCE_VIEW')")
    @GetMapping("/attendance/{id}")
    public ResponseEntity<HostelAttendance> getAttendanceById(@PathVariable Long id) {
        return ResponseEntity.ok(hostelService.getAttendanceById(id));
    }

    //  PATCH (UPDATE STATUS ONLY)
    @PreAuthorize("hasAuthority('HOSTEL_ATTENDANCE_UPDATE')")
    @PatchMapping("/attendance/{id}")
    public ResponseEntity<HostelAttendance> updateAttendance(
            @PathVariable Long id,
            @RequestBody HostelAttendance attendance) {
        return ResponseEntity.ok(
        		hostelService.updateAttendancePartial(id, attendance));
    }
 
    //  DELETE ATTENDANCE
    @PreAuthorize("hasAuthority('HOSTEL_ATTENDANCE_DELETE')")
    @DeleteMapping("/attendance/{id}")
    public ResponseEntity<String> deleteAttendance(@PathVariable Long id) {
    	hostelService.deleteAttendance(id);
        return ResponseEntity.ok("Attendance deleted successfully");
    }
    
    //===================HostelComplaints====================//
    
    
    //CREATE COMPLAINT (STUDENT)
    @PreAuthorize("hasAuthority('HOSTEL_COMPLAINT_CREATE')")
    @PostMapping("/complaint")
    public ResponseEntity<HostelComplaint> createComplaint(
            @RequestBody HostelComplaint complaint) {
        return ResponseEntity.ok(hostelService.createComplaint(complaint));
    }

    //  GET ALL COMPLAINTS
    @PreAuthorize("hasAuthority('HOSTEL_COMPLAINT_VIEW')")
    @GetMapping("/complaints")
    public ResponseEntity<List<HostelComplaint>> getAllComplaints(
            @RequestParam(required = false)
            HostelComplaint.ComplaintStatus status) {
        return ResponseEntity.ok(
        		hostelService.getAllComplaints(status));
    }

    //  GET COMPLAINT BY ID
    @PreAuthorize("hasAuthority('HOSTEL_COMPLAINT_VIEW')")
    @GetMapping("/complaint/{id}")
    public ResponseEntity<HostelComplaint> getComplaintById(
            @PathVariable Long id) {
        return ResponseEntity.ok(
        		hostelService.getComplaintById(id));
    }

 // PUT – FULL UPDATE COMPLAINT
    @PreAuthorize("hasAuthority('HOSTEL_COMPLAINT_UPDATE')")
    @PutMapping("/complaint/{id}")
    public ResponseEntity<HostelComplaint> updateComplaintFull(
            @PathVariable Long id,
            @RequestBody HostelComplaint complaint) {

        return ResponseEntity.ok(
                hostelService.updateComplaintFull(id, complaint)
        );
    }
    // PATCH – UPDATE STATUS / REMARKS (ADMIN)
    @PreAuthorize("hasAuthority('HOSTEL_COMPLAINT_STATUS_UPDATE')")
    @PatchMapping("/complaint/{id}")
    public ResponseEntity<HostelComplaint> updateComplaint(
            @PathVariable Long id,
            @RequestParam(required = false)
            HostelComplaint.ComplaintStatus status,
            @RequestParam(required = false)
            String adminRemarks) {

        return ResponseEntity.ok(
        		hostelService.updateComplaint(id, status, adminRemarks));
    }

    //  DELETE COMPLAINT
    @PreAuthorize("hasAuthority('HOSTEL_COMPLAINT_DELETE')")
    @DeleteMapping("/complaint/{id}")
    public ResponseEntity<String> deleteComplaint(@PathVariable Long id) {
    	hostelService.deleteComplaint(id);
        return ResponseEntity.ok("Complaint deleted successfully");
    }
    //=====================MessMenu================================//
  
    //  CREATE MENU
    @PreAuthorize("hasAuthority('MESS_MENU_CREATE')")
    @PostMapping("/mess-menu")
    public ResponseEntity<MessDayMenu> createMenu(
            @RequestBody MessDayMenu menu) {
        return ResponseEntity.ok(hostelService.createMenu(menu));
    }

    // GET ALL MENUS
    @PreAuthorize("hasAuthority('MESS_MENU_VIEW')")
    @GetMapping("/mess-menus")
    public ResponseEntity<List<MessDayMenu>> getAllMenus() {
        return ResponseEntity.ok(hostelService.getAllMenus());
    }

    // GET MENU BY ID
    @PreAuthorize("hasAuthority('MESS_MENU_VIEW')")
    @GetMapping("/mess-menus/{id}")
    public ResponseEntity<MessDayMenu> getMenuById(@PathVariable Long id) {
        return ResponseEntity.ok(hostelService.getMenuById(id));
    }

    // PATCH (PARTIAL UPDATE)
    @PreAuthorize("hasAuthority('MESS_MENU_UPDATE')")
    @PatchMapping("/mess-menu/{id}")
    public ResponseEntity<MessDayMenu> updateMenu(
            @PathVariable Long id,
            @RequestBody MessDayMenu menu) {
        return ResponseEntity.ok(
        		hostelService.updateMenuPartial(id, menu));
    }
 // PUT (FULL UPDATE)
    @PreAuthorize("hasAuthority('MESS_MENU_UPDATE')")
    @PutMapping("/mess-menu/{id}")
    public ResponseEntity<MessDayMenu> updateMenuFull(
            @PathVariable Long id,
            @RequestBody MessDayMenu menu) {

        return ResponseEntity.ok(hostelService.updateMenu(id, menu));
    }

    // DELETE MENU
    @PreAuthorize("hasAuthority('MESS_MENU_DELETE')")
    @DeleteMapping("/mess-menu/{id}")
    public ResponseEntity<String> deleteMenu(@PathVariable Long id) {
    	hostelService.deleteMenu(id);
        return ResponseEntity.ok("Menu deleted successfully");
    }
//=================== StudentHealthIncidentController =================//
    
    //  CREATE HEALTH INCIDENT
    @PreAuthorize("hasAuthority('HOSTEL_HEALTH_INCIDENT_CREATE')")
    @PostMapping("/health")
    public ResponseEntity<StudentHealthIncident> createIncident(
            @RequestBody StudentHealthIncident incident) {
        return ResponseEntity.ok(hostelService.createIncident(incident));
    }

    //  GET ALL INCIDENTS
    @PreAuthorize("hasAuthority('HOSTEL_HEALTH_INCIDENT_VIEW')")
    @GetMapping("/health")
    public ResponseEntity<List<StudentHealthIncident>> getAllIncidents() {
        return ResponseEntity.ok(hostelService.getAllIncidents());
    }

    // GET INCIDENT BY ID
    @PreAuthorize("hasAuthority('HOSTEL_HEALTH_INCIDENT_VIEW')")
    @GetMapping("/health/{id}")
    public ResponseEntity<StudentHealthIncident> getIncidentById(
            @PathVariable Long id) {
        return ResponseEntity.ok(hostelService.getIncidentById(id));
    }

    // PATCH – UPDATE STATUS / CLINICAL NOTES
    @PreAuthorize("hasAuthority('HOSTEL_HEALTH_INCIDENT_STATUS_UPDATE')")
    @PatchMapping("/health/{id}")
    public ResponseEntity<StudentHealthIncident> updateIncident(
            @PathVariable Long id,
            @RequestParam(required = false)
            StudentHealthIncident.IncidentStatus status,
            @RequestParam(required = false)
            String clinicalNotes) {

        return ResponseEntity.ok(
        		hostelService.updateIncident(id, status, clinicalNotes));
    }
     // PUT – FULL UPDATE HEALTH INCIDENT
    
    @PreAuthorize("hasAuthority('HOSTEL_HEALTH_INCIDENT_UPDATE')")
    @PutMapping("/health/{id}")
    public ResponseEntity<StudentHealthIncident> updateIncidentFull(
            @PathVariable Long id,
            @RequestBody StudentHealthIncident incident) {

        return ResponseEntity.ok(
                hostelService.updateIncidentFull(id, incident)
        );
    }


    // DELETE (SOFT DELETE)
    @PreAuthorize("hasAuthority('HOSTEL_HEALTH_INCIDENT_DELETE')")
    @DeleteMapping("/health/{id}")
    public ResponseEntity<String> deleteIncident(@PathVariable Long id) {
    	hostelService.deleteIncident(id);
        return ResponseEntity.ok("Health incident deleted successfully");
    }
    // ================= StudentHostelAllocation ====================//
        
        
 // CREATE ALLOCATION
    @PreAuthorize("hasAuthority('HOSTEL_ALLOCATION_CREATE')")
    @PostMapping("/allocations")
    public ResponseEntity<StudentHostelAllocation> createAllocation(
            @RequestBody StudentHostelAllocation allocation) {
        return ResponseEntity.ok(
        		hostelService.createAllocation(allocation));
    }

    // GET ALL ALLOCATIONS
    @PreAuthorize("hasAuthority('HOSTEL_ALLOCATION_VIEW')")
    @GetMapping("/allocations")
    public ResponseEntity<List<StudentHostelAllocation>> getAllAllocations(
            @RequestParam(required = false)
            StudentHostelAllocation.AllocationStatus status) {

        return ResponseEntity.ok(
        		hostelService.getAllAllocations(status));
    }

    // GET ALLOCATION BY ID
    @PreAuthorize("hasAuthority('HOSTEL_ALLOCATION_VIEW')")
    @GetMapping("/allocations/{id}")
    public ResponseEntity<StudentHostelAllocation> getAllocationById(
            @PathVariable Long id) {
        return ResponseEntity.ok(
        		hostelService.getAllocationById(id));
    }

    //  PATCH – UPDATE STATUS (CHECKOUT / CANCEL)
    @PreAuthorize("hasAuthority('HOSTEL_ALLOCATION_STATUS_UPDATE')")
    @PatchMapping("/allocations/{id}/status")
    public ResponseEntity<StudentHostelAllocation> updateAllocationStatus(
            @PathVariable Long id,
            @RequestParam
            StudentHostelAllocation.AllocationStatus status,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate leaveDate) {

        return ResponseEntity.ok(
        		hostelService.updateAllocationStatus(id, status, leaveDate));
    }

    // PATCH – UPDATE PAYMENT
    @PreAuthorize("hasAuthority('HOSTEL_ALLOCATION_PAYMENT_UPDATE')")
    @PatchMapping("/allocations/{id}/payment")
    public ResponseEntity<StudentHostelAllocation> updatePayment(
            @PathVariable Long id,
            @RequestParam BigDecimal amountPaid,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate paymentDate) {

        return ResponseEntity.ok(
        		hostelService.updatePayment(id, amountPaid, paymentDate));
    }
       // PUT – FULL UPDATE ALLOCATION
    @PreAuthorize("hasAuthority('HOSTEL_ALLOCATION_UPDATE')")
    @PutMapping("/allocations/{id}")
    public ResponseEntity<StudentHostelAllocation> updateAllocation(
            @PathVariable Long id,
            @RequestBody StudentHostelAllocation allocation) {

        return ResponseEntity.ok(
                hostelService.updateAllocation(id, allocation));
    }
    
 // DELETE – SOFT DELETE ALLOCATION
    @PreAuthorize("hasAuthority('HOSTEL_ALLOCATION_DELETE')")
    @DeleteMapping("/allocations/{id}")
    public ResponseEntity<String> deleteAllocation(@PathVariable Long id) {

        hostelService.deleteAllocation(id);
        return ResponseEntity.ok("Allocation deleted successfully");
    }


    //=======================================StudentVisitEntryController====================//
    
    // CREATE VISIT (STUDENT)
    @PreAuthorize("hasAuthority('HOSTEL_VISIT_CREATE')")
    @PostMapping("/visits")
    public ResponseEntity<StudentVisitEntry> createVisit(
            @RequestBody StudentVisitEntry visit) {
        return ResponseEntity.ok(hostelService.createVisit(visit));
    }

    // GET ALL VISITS (OPTIONAL DATE FILTER)
    @PreAuthorize("hasAuthority('HOSTEL_VISIT_VIEW')")
    @GetMapping("/visits")
    public ResponseEntity<List<StudentVisitEntry>> getAllVisits(
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate date) {

        return ResponseEntity.ok(hostelService.getAllVisits(date));
    }

    // GET VISIT BY ID
    @PreAuthorize("hasAuthority('HOSTEL_VISIT_VIEW')")
    @GetMapping("/visits/{id}")
    public ResponseEntity<StudentVisitEntry> getVisitById(
            @PathVariable Long id) {
        return ResponseEntity.ok(hostelService.getVisitById(id));
    }

    //  PATCH – UPDATE STATUS
    @PreAuthorize("hasAuthority('HOSTEL_VISIT_STATUS_UPDATE')")
    @PatchMapping("/visits/{id}/status")
    public ResponseEntity<StudentVisitEntry> updateVisitStatus(
            @PathVariable Long id,
            @RequestParam StudentVisitEntry.VisitStatus status) {

        return ResponseEntity.ok(
        		hostelService.updateVisitStatus(id, status));
    }
 // PUT – UPDATE VISIT (FULL UPDATE)
    @PreAuthorize("hasAuthority('HOSTEL_VISIT_UPDATE')")
    @PutMapping("/visits/{id}")
    public ResponseEntity<StudentVisitEntry> updateVisit(
            @PathVariable Long id,
            @RequestBody StudentVisitEntry visit) {

        return ResponseEntity.ok(
                hostelService.updateVisit(id, visit));
    }


    //  DELETE VISIT
    @PreAuthorize("hasAuthority('HOSTEL_VISIT_DELETE')")
    @DeleteMapping("/visits/{id}")
    public ResponseEntity<String> deleteVisit(@PathVariable Long id) {
    	hostelService.deleteVisit(id);
        return ResponseEntity.ok("Visit entry deleted successfully");
    }
   
       
    }
