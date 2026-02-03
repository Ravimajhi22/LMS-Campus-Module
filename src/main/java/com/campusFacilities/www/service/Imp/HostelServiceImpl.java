package com.campusFacilities.www.service.Imp;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.campusFacilities.www.model.Hostel.Hostel;
import com.campusFacilities.www.model.Hostel.HostelAttendance;
import com.campusFacilities.www.model.Hostel.HostelComplaint;
import com.campusFacilities.www.model.Hostel.HostelRoom;
import com.campusFacilities.www.model.Hostel.MessDayMenu;
import com.campusFacilities.www.model.Hostel.StudentHealthIncident;
import com.campusFacilities.www.model.Hostel.StudentHostelAllocation;
import com.campusFacilities.www.model.Hostel.StudentVisitEntry;
import com.campusFacilities.www.repository.Hostel.HostelAttendanceRepository;
import com.campusFacilities.www.repository.Hostel.HostelComplaintRepository;
import com.campusFacilities.www.repository.Hostel.HostelRepository;
import com.campusFacilities.www.repository.Hostel.HostelRoomRepository;
import com.campusFacilities.www.repository.Hostel.MessDayMenuRepository;
import com.campusFacilities.www.repository.Hostel.StudentHealthIncidentRepository;
import com.campusFacilities.www.repository.Hostel.StudentHostelAllocationRepository;
import com.campusFacilities.www.repository.Hostel.StudentVisitEntryRepository;

import jakarta.transaction.Transactional;

@Service
public class HostelServiceImpl {

    @Autowired
    private HostelRepository hostelRepository;

    @Autowired
    private HostelAttendanceRepository hostelAttedanceRepository;
    
    @Autowired
    private HostelRoomRepository hostelRoomRepository;
    
    @Autowired
    private StudentHealthIncidentRepository incidentRepository;
    
    @Autowired
    private HostelComplaintRepository hostelComplaintRepository;
    
    @Autowired
    private MessDayMenuRepository  messDayMenuRepository;
    
    @Autowired
    private StudentHostelAllocationRepository allocationRepository;

    @Autowired
    private HostelComplaintRepository complaintRepository;
    
    @Autowired 
    private StudentVisitEntryRepository visitRepository;

    // ================= HOSTEL =================

    public Hostel createHostel(Hostel hostel) {
        if (hostelRepository.existsByHostelNameAndIsDeletedFalse(hostel.getHostelName())) {
            throw new RuntimeException("Hostel already exists");
        }
        return hostelRepository.save(hostel);
    }

    // GET ALL
    public List<Hostel> getAllHostels() {
        return hostelRepository.findByIsDeletedFalse();
    }

    // GET BY ID
    public Hostel getHostelById(Long hostelId) {
        return hostelRepository.findById(hostelId)
                .filter(h -> !Boolean.TRUE.equals(h.getIsDeleted()))
                .orElseThrow(() -> new RuntimeException("Hostel not found"));
    }
    
    //PUT Method
    public Hostel updateHostel(Long id, Hostel hostel) {

        Hostel existingHostel = hostelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hostel not found"));

        existingHostel.setHostelName(hostel.getHostelName());
        existingHostel.setWardenName(hostel.getWardenName());
        existingHostel.setContactNumber(hostel.getContactNumber());
        existingHostel.setHostelType(hostel.getHostelType());
        existingHostel.setTotalBlocks(hostel.getTotalBlocks());
        existingHostel.setTotalRooms(hostel.getTotalRooms());
        existingHostel.setStatus(hostel.getStatus());

        return hostelRepository.save(existingHostel);
    }

    // PATCH (PARTIAL UPDATE)
    public Hostel updateHostelPartial(Long hostelId, Hostel request) {
        Hostel hostel = getHostelById(hostelId);

        if (request.getHostelName() != null)
            hostel.setHostelName(request.getHostelName());

        if (request.getHostelType() != null)
            hostel.setHostelType(request.getHostelType());

        if (request.getWardenName() != null)
            hostel.setWardenName(request.getWardenName());

        if (request.getContactNumber() != null)
            hostel.setContactNumber(request.getContactNumber());

        return hostelRepository.save(hostel);
    }

    // PATCH STATUS
    public Hostel updateStatus(Long hostelId, Hostel.Status status) {
        Hostel hostel = getHostelById(hostelId);
        hostel.setStatus(status);
        return hostelRepository.save(hostel);
    }

    // SOFT DELETE
    public void deleteHostel(Long hostelId) {
        Hostel hostel = getHostelById(hostelId);
        hostel.setIsDeleted(true);
        hostelRepository.save(hostel);
    }
    
    //=================HostelAttendance===========================================//
    
    // MARK ATTENDANCE
    public HostelAttendance markAttendance(HostelAttendance attendance) {
        return hostelAttedanceRepository.save(attendance);
    }
    

    // GET ALL ATTENDANCE (OPTIONAL FILTER BY DATE)
    public List<HostelAttendance> getAllAttendance(LocalDate date) {
        if (date != null) {
            return hostelAttedanceRepository.findByAttendanceDate(date);
        }
        return hostelAttedanceRepository.findAll();
    }

    // GET BY ID
    public HostelAttendance getAttendanceById(Long attendanceId) {
        return hostelAttedanceRepository.findById(attendanceId)
                .orElseThrow(() -> new RuntimeException("Attendance not found"));
    }
  
    // PATCH (PARTIAL UPDATE)
    public HostelAttendance updateAttendancePartial(Long attendanceId, HostelAttendance request) {
        HostelAttendance attendance = getAttendanceById(attendanceId);

        if (request.getStatus() != null)
            attendance.setStatus(request.getStatus());

        return hostelAttedanceRepository.save(attendance);
    }

    // DELETE (HARD DELETE OR CHANGE TO SOFT IF NEEDED)
    public void deleteAttendance(Long attendanceId) {
    	hostelAttedanceRepository.deleteById(attendanceId);
    }

//====================HostelComplaints===============================//
  
    //CREATE COMPLAINT
     public HostelComplaint addComplaint(HostelComplaint complaint) {
    complaint.setReportedDate(LocalDate.now());
    complaint.setStatus(HostelComplaint.ComplaintStatus.OPEN);
    return hostelComplaintRepository.save(complaint);
}

    // GET ALL COMPLAINTS (OPTIONAL FILTER BY STATUS)
   public List<HostelComplaint> getAllComplaints(
        HostelComplaint.ComplaintStatus status) {

    if (status != null) {
        return hostelComplaintRepository.findByStatus(status);
    }
    return hostelComplaintRepository.findAll();
}

// GET COMPLAINT BY ID
public HostelComplaint getComplaintById(Long complaintId) {
    return hostelComplaintRepository.findById(complaintId)
            .orElseThrow(() -> new RuntimeException("Complaint not found"));
}
public HostelComplaint updateComplaintFull(Long id, HostelComplaint updated) {

    HostelComplaint existing = complaintRepository.findById(id)
            .orElseThrow(() ->
                    new RuntimeException("Complaint not found with id: " + id));

    // -------- STUDENT INFO --------
    existing.setStudentId(updated.getStudentId());
    existing.setStudentName(updated.getStudentName());
    existing.setStudentEmail(updated.getStudentEmail());
    existing.setStudentPhone(updated.getStudentPhone());

  
    // -------- COMPLAINT DETAILS --------
    existing.setIssueCategory(updated.getIssueCategory());
    existing.setPriority(updated.getPriority());
    existing.setDescription(updated.getDescription());
    existing.setReportedDate(updated.getReportedDate());
    existing.setStatus(updated.getStatus());
    existing.setAdminRemarks(updated.getAdminRemarks());

    return complaintRepository.save(existing);
}

// PATCH – UPDATE STATUS / ADMIN REMARKS ONLY
public HostelComplaint updateComplaint(
        Long complaintId,
        HostelComplaint.ComplaintStatus status,
        String adminRemarks) {

    HostelComplaint complaint = getComplaintById(complaintId);

    if (status != null)
        complaint.setStatus(status);

    if (adminRemarks != null)
        complaint.setAdminRemarks(adminRemarks);

    return hostelComplaintRepository.save(complaint);
}

     // DELETE (OPTIONAL – ADMIN / SUPER_ADMIN)
    public void deleteComplaint(Long complaintId) {
    hostelComplaintRepository.deleteById(complaintId);
}
    // ================================ HostelRooms ==============================//

    // CREATE ROOM
    public HostelRoom createRoom(HostelRoom room) {
        return hostelRoomRepository.save(room);
    }

   
    // GET ALL ROOMS
    public List<HostelRoom> getAllRooms() {
        return hostelRoomRepository.findByIsDeletedFalse();
    }

    // GET ROOM BY ID
    public HostelRoom getRoomById(Long roomId) {
        return hostelRoomRepository.findById(roomId)
                .filter(r -> !Boolean.TRUE.equals(r.getIsDeleted()))
                .orElseThrow(() -> new RuntimeException("Room not found"));
    }

    // PATCH (PARTIAL UPDATE)
    public HostelRoom updateRoomPartial(Long roomId, HostelRoom request) {
        HostelRoom room = getRoomById(roomId);

        if (request.getRoomNumber() != null)
            room.setRoomNumber(request.getRoomNumber());

        if (request.getSharingType() != null)
            room.setSharingType(request.getSharingType());

        return hostelRoomRepository.save(room);
    }

    // PATCH STATUS
    public HostelRoom updateRoomStatus(Long roomId, HostelRoom.RoomStatus status) {
        HostelRoom room = getRoomById(roomId);
        room.setStatus(status);
        return hostelRoomRepository.save(room);
    }

    //Put 
    public HostelRoom updateRoom(Long id, HostelRoom room) {

        HostelRoom existingRoom = hostelRoomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Room not found"));

        // REQUIRED FIELDS
        existingRoom.setRoomNumber(room.getRoomNumber());
        existingRoom.setSharingType(room.getSharingType());
        existingRoom.setStatus(room.getStatus());

        // OPTIONAL FIELDS (safe updates)
        existingRoom.setCurrentlyOccupied(room.getCurrentlyOccupied());
        existingRoom.setIsDeleted(room.getIsDeleted());

        return hostelRoomRepository.save(existingRoom);
    }
    
    // SOFT DELETE
    public void deleteRoom(Long roomId) {
        HostelRoom room = getRoomById(roomId);
        room.setIsDeleted(true);
        hostelRoomRepository.save(room);
    }
    
    //==================MessMenu===========================//
    
    public MessDayMenu createMenu(MessDayMenu menu) {
        messDayMenuRepository.findByDay(menu.getDay())
                .ifPresent(existing -> {
                    throw new RuntimeException("Menu already exists for " + menu.getDay());
                });
        return messDayMenuRepository.save(menu);
    }

    // GET ALL MENUS
    public List<MessDayMenu> getAllMenus() {
        return messDayMenuRepository.findAll();
    }

    // GET MENU BY ID
    public MessDayMenu getMenuById(Long menuId) {
        return messDayMenuRepository.findById(menuId)
                .orElseThrow(() -> new RuntimeException("Menu not found"));
    }

    //PUT 
    public MessDayMenu updateMenu(Long id, MessDayMenu menu) {

        MessDayMenu existingMenu = messDayMenuRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mess menu not found"));

        // FULL UPDATE
        existingMenu.setDay(menu.getDay());
        existingMenu.setBreakfast(menu.getBreakfast());
        existingMenu.setLunch(menu.getLunch());
        existingMenu.setDinner(menu.getDinner());

        return messDayMenuRepository.save(existingMenu);
    }
    // PATCH (PARTIAL UPDATE)
    public MessDayMenu updateMenuPartial(Long menuId, MessDayMenu request) {
        MessDayMenu menu = getMenuById(menuId);

        if (request.getBreakfast() != null)
            menu.setBreakfast(request.getBreakfast());

        if (request.getLunch() != null)
            menu.setLunch(request.getLunch());

        if (request.getDinner() != null)
            menu.setDinner(request.getDinner());

        return messDayMenuRepository.save(menu);
    }

    // DELETE MENU
    public void deleteMenu(Long menuId) {
        messDayMenuRepository.deleteById(menuId);
    }

    
    //================StudentHealthIncidentService===================//
    
    // CREATE INCIDENT
    public StudentHealthIncident createIncident(StudentHealthIncident incident) {
        incident.setReportedDate(LocalDate.now());
        incident.setCurrentStatus(
                StudentHealthIncident.IncidentStatus.OBSERVATION);
        return incidentRepository.save(incident);
    }

    // GET ALL INCIDENTS
    public List<StudentHealthIncident> getAllIncidents() {
        return incidentRepository.findByIsDeletedFalse();
    }

    // GET INCIDENT BY ID
    public StudentHealthIncident getIncidentById(Long incidentId) {
        return incidentRepository.findById(incidentId)
                .filter(i -> !Boolean.TRUE.equals(i.getIsDeleted()))
                .orElseThrow(() -> new RuntimeException("Health incident not found"));
    }

    // PATCH – UPDATE STATUS / NOTES ONLY
    public StudentHealthIncident updateIncident(
            Long incidentId,
            StudentHealthIncident.IncidentStatus status,
            String clinicalNotes) {

        StudentHealthIncident incident = getIncidentById(incidentId);

        if (status != null)
            incident.setCurrentStatus(status);

        if (clinicalNotes != null)
            incident.setClinicalNotes(clinicalNotes);

        return incidentRepository.save(incident);
    }
    //Put method
    public StudentHealthIncident updateIncidentFull(
            Long id,
            StudentHealthIncident updated) {

        StudentHealthIncident existing = getIncidentById(id);

        existing.setStudentId(updated.getStudentId());
        existing.setStudentName(updated.getStudentName());
        existing.setStudentPhone(updated.getStudentPhone());
        existing.setParentPhone(updated.getParentPhone());
        existing.setComplaintNature(updated.getComplaintNature());
        existing.setSeverity(updated.getSeverity());
        existing.setCurrentStatus(updated.getCurrentStatus());
        existing.setReportedDate(updated.getReportedDate());
        existing.setClinicalNotes(updated.getClinicalNotes());
       
        // hostel & room (important)
        existing.setHostel(updated.getHostel());
        existing.setRoom(updated.getRoom());

        return incidentRepository.save(existing);
    }

    // SOFT DELETE
    public void deleteIncident(Long incidentId) {
        StudentHealthIncident incident = getIncidentById(incidentId);
        incident.setIsDeleted(true);
        incidentRepository.save(incident);
    }

    // ================= StudentHostelAllocationService=================

// CREATE ALLOCATION
    @Transactional
    public StudentHostelAllocation createAllocation(StudentHostelAllocation request) {

        //  Fetch managed Hostel
        Hostel hostel = hostelRepository.findById(
                request.getHostel().getHostelId()
        ).orElseThrow(() -> new RuntimeException("Hostel not found"));

        //  Fetch managed Room
        HostelRoom room = hostelRoomRepository.findById(
                request.getRoom().getRoomId()
        ).orElseThrow(() -> new RuntimeException("Room not found"));

        // Create NEW allocation 
        StudentHostelAllocation allocation = new StudentHostelAllocation();

        // Copy student data (from other DB)
        allocation.setStudentId(request.getStudentId());
        allocation.setStudentName(request.getStudentName());
        allocation.setStudentEmail(request.getStudentEmail());
        allocation.setParentName(request.getParentName());
        allocation.setParentPhone(request.getParentPhone());

        //  Set manage entities ONLY
        allocation.setHostel(hostel);
        allocation.setRoom(room);

        allocation.setJoinDate(request.getJoinDate());
        allocation.setLeaveDate(request.getLeaveDate());
        allocation.setStatus(request.getStatus());

        return allocationRepository.save(allocation);
    }

// GET ALL ALLOCATIONS (OPTIONAL FILTER BY STATUS)
public List<StudentHostelAllocation> getAllAllocations(
        StudentHostelAllocation.AllocationStatus status) {

    if (status != null) {
        return allocationRepository.findByStatus(status);
    }
    return allocationRepository.findAll();
}

// GET ALLOCATION BY ID
public StudentHostelAllocation getAllocationById(Long allocationId) {
    return allocationRepository.findById(allocationId)
            .orElseThrow(() -> new RuntimeException("Allocation not found"));
}

// PATCH – UPDATE ALLOCATION STATUS (CHECKOUT / CANCEL)
public StudentHostelAllocation updateAllocationStatus(
        Long allocationId,
        StudentHostelAllocation.AllocationStatus status,
        LocalDate leaveDate) {

    StudentHostelAllocation allocation = getAllocationById(allocationId);

    if (status != null) {
        allocation.setStatus(status);
    }

    if (leaveDate != null) {
        allocation.setLeaveDate(leaveDate);
    }

    return allocationRepository.save(allocation);
}

// PATCH – UPDATE PAYMENT DETAILS
public StudentHostelAllocation updatePayment(
        Long allocationId,
        BigDecimal amountPaid,
        LocalDate paymentDate) {

    StudentHostelAllocation allocation = getAllocationById(allocationId);

    allocation.setAmountPaid(
            allocation.getAmountPaid().add(amountPaid));

    allocation.setDueAmount(
            allocation.getTotalFee().subtract(allocation.getAmountPaid()));

    allocation.setPaymentStatus(
            allocation.getDueAmount().compareTo(BigDecimal.ZERO) == 0
                    ? StudentHostelAllocation.PaymentStatus.PAID
                    : StudentHostelAllocation.PaymentStatus.DUE);

    allocation.setLastPaymentDate(paymentDate != null
            ? paymentDate
            : LocalDate.now());

    return allocationRepository.save(allocation);
}
 
      //Put Method
    public StudentHostelAllocation updateAllocation(Long id, StudentHostelAllocation allocation) {

        StudentHostelAllocation existing = allocationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Allocation not found"));

        existing.setStudentName(allocation.getStudentName());
        existing.setStudentEmail(allocation.getStudentEmail());
        existing.setParentName(allocation.getParentName());
        existing.setParentPhone(allocation.getParentPhone());
        existing.setRoom(allocation.getRoom());
        existing.setJoinDate(allocation.getJoinDate());
        existing.setLeaveDate(allocation.getLeaveDate());
        existing.setMonthlyFee(allocation.getMonthlyFee());
        existing.setTotalFee(allocation.getTotalFee());
        existing.setAmountPaid(allocation.getAmountPaid());
        existing.setDueAmount(allocation.getDueAmount());
        existing.setPaymentStatus(allocation.getPaymentStatus());
        existing.setLastPaymentDate(allocation.getLastPaymentDate());
        existing.setStatus(allocation.getStatus());
       
        return allocationRepository.save(existing);
        
    }
    public void deleteAllocation(Long id) {

        StudentHostelAllocation allocation = allocationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Allocation not found"));

        allocation.setStatus(StudentHostelAllocation.AllocationStatus.CANCELLED);
        allocation.setLeaveDate(LocalDate.now());

        allocationRepository.save(allocation);
    }

    // ================= Hostel complaints =================

    public HostelComplaint createComplaint(HostelComplaint complaint) {
        complaint.setReportedDate(LocalDate.now());
        complaint.setStatus(HostelComplaint.ComplaintStatus.OPEN);
        return complaintRepository.save(complaint);
    }

    // READ
    public List<HostelComplaint> getAllComplaints() {
        return complaintRepository.findAll();
    }

    public HostelComplaint getById(Long id) {
        return complaintRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Complaint not found"));
    }

    // PATCH (PARTIAL UPDATE)
    public HostelComplaint patchComplaint(Long id, HostelComplaint req) {
        HostelComplaint existing = getById(id);

        if (req.getIssueCategory() != null)
            existing.setIssueCategory(req.getIssueCategory());

        if (req.getPriority() != null)
            existing.setPriority(req.getPriority());

        if (req.getDescription() != null)
            existing.setDescription(req.getDescription());

        if (req.getStatus() != null)
            existing.setStatus(req.getStatus());

        if (req.getAdminRemarks() != null)
            existing.setAdminRemarks(req.getAdminRemarks());

        return complaintRepository.save(existing);
    }

    // DELETE (Soft Close)
    public void closeComplaint(Long id) {
        HostelComplaint complaint = getById(id);
        complaint.setStatus(HostelComplaint.ComplaintStatus.CLOSED);
        complaintRepository.save(complaint);
    }
        
        //====================StudentVisitEntryService====================//
    
        // CREATE VISIT ENTRY (STUDENT)
        public StudentVisitEntry createVisit(StudentVisitEntry visit) {
            visit.setStatus(StudentVisitEntry.VisitStatus.SCHEDULED);
            visit.setCreatedAt(LocalDate.now());
            return visitRepository.save(visit);
        }

        // GET ALL VISITS (OPTIONAL DATE FILTER)
        public List<StudentVisitEntry> getAllVisits(LocalDate date) {
            if (date != null) {
                return visitRepository.findByVisitDate(date);
            }
            return visitRepository.findAll();
        }

        // GET VISIT BY ID
        public StudentVisitEntry getVisitById(Long visitId) {
            return visitRepository.findById(visitId)
                    .orElseThrow(() -> new RuntimeException("Visit entry not found"));
        }

        // PATCH – UPDATE VISIT STATUS (CHECK-IN / CHECK-OUT / CANCEL)
        public StudentVisitEntry updateVisitStatus(
                Long visitId,
                StudentVisitEntry.VisitStatus status) {

            StudentVisitEntry visit = getVisitById(visitId);
            visit.setStatus(status);
            return visitRepository.save(visit);
        }
        
        public StudentVisitEntry updateVisit(Long id, StudentVisitEntry visit) {

            StudentVisitEntry existing = visitRepository.findById(id)
                    .orElseThrow(() ->
                            new RuntimeException("Visit entry not found with id: " + id));

            // -------- FULL UPDATE --------
            existing.setStudentId(visit.getStudentId());
            existing.setStudentName(visit.getStudentName());
            existing.setVisitorName(visit.getVisitorName());
    
            existing.setVisitorContact(visit.getVisitorContact());
            existing.setPurposeOfVisit(visit.getPurposeOfVisit());
            existing.setVisitDate(visit.getVisitDate());
            existing.setVisitTime(visit.getVisitTime());
            existing.setVisitTime(visit.getVisitTime());
            existing.setStatus(visit.getStatus());

            return visitRepository.save(existing);
        }

        // DELETE VISIT ENTRY (RARE / SUPER ADMIN)
        public void deleteVisit(Long visitId) {
            visitRepository.deleteById(visitId);
        }
    
    }
