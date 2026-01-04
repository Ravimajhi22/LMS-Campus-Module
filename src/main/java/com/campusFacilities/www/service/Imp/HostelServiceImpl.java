package com.campusFacilities.www.service.Imp;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.campusFacilities.www.model.Hostel.Hostel;
import com.campusFacilities.www.model.Hostel.HostelBlock;
import com.campusFacilities.www.model.Hostel.HostelComplaint;
import com.campusFacilities.www.model.Hostel.HostelFee;
import com.campusFacilities.www.model.Hostel.HostelRoom;
import com.campusFacilities.www.model.Hostel.StudentHostelAllocation;
import com.campusFacilities.www.repository.Hostel.HostelBlockRepository;
import com.campusFacilities.www.repository.Hostel.HostelComplaintRepository;
import com.campusFacilities.www.repository.Hostel.HostelFeeRepository;
import com.campusFacilities.www.repository.Hostel.HostelRepository;
import com.campusFacilities.www.repository.Hostel.HostelRoomRepository;
import com.campusFacilities.www.repository.Hostel.StudentHostelAllocationRepository;

@Service
public class HostelServiceImpl {

    @Autowired
    private HostelRepository hostelRepository;

    @Autowired
    private HostelBlockRepository blockRepository;

    @Autowired
    private HostelRoomRepository roomRepository;

    @Autowired
    private StudentHostelAllocationRepository allocationRepository;

    @Autowired
    private HostelFeeRepository feeRepository;

    @Autowired
    private HostelComplaintRepository complaintRepository;

    // ================= HOSTEL =================

    public Hostel addHostel(Hostel hostel) {
        hostel.setTotalBlocks(0);
        hostel.setTotalRooms(0);
        return hostelRepository.save(hostel);
    }

    public List<Hostel> getAllHostels() {
        return hostelRepository.findAll();
    }

    public Hostel updateHostel(Long hostelId, Hostel updatedHostel) {
        Hostel existing = hostelRepository.findById(hostelId)
                .orElseThrow(() -> new RuntimeException("Hostel not found with id: " + hostelId));

        existing.setHostelName(updatedHostel.getHostelName());
        existing.setHostelType(updatedHostel.getHostelType());
        existing.setWardenName(updatedHostel.getWardenName());
        existing.setContactNumber(updatedHostel.getContactNumber());
        existing.setStatus(updatedHostel.getStatus());

        return hostelRepository.save(existing);
    }

    public void deleteHostel(Long hostelId) {
        Hostel hostel = hostelRepository.findById(hostelId)
                .orElseThrow(() -> new RuntimeException("Hostel not found with id: " + hostelId));

        hostel.setIsDeleted(true);
        hostelRepository.save(hostel);
    }

    // ================= HostelBlocks =================

    public HostelBlock addBlock(HostelBlock block) {
        return blockRepository.save(block);
    }

    public List<HostelBlock> getAllBlocks() {
        return blockRepository.findAll();
    }

    public HostelBlock updateBlock(Long blockId, HostelBlock updatedBlock) {
        HostelBlock existing = blockRepository.findById(blockId)
                .orElseThrow(() -> new RuntimeException("Block not found with id: " + blockId));

        existing.setBlockName(updatedBlock.getBlockName());
        existing.setTotalFloors(updatedBlock.getTotalFloors());
        existing.setStatus(updatedBlock.getStatus());

        return blockRepository.save(existing);
    }

    public void deleteBlock(Long blockId) {
        HostelBlock block = blockRepository.findById(blockId)
                .orElseThrow(() -> new RuntimeException("Block not found with id: " + blockId));

        block.setStatus(HostelBlock.Status.INACTIVE);
        blockRepository.save(block);
    }

    // ================= HostelRooms=================

    public HostelRoom addRoom(HostelRoom room) {
        room.setOccupiedCount(0);
        room.setStatus(HostelRoom.RoomStatus.AVAILABLE);
        return roomRepository.save(room);
    }

    public List<HostelRoom> getAllRooms() {
        return roomRepository.findAll();
    }

    public HostelRoom updateRoom(Long roomId, HostelRoom updatedRoom) {
        HostelRoom existing = roomRepository.findById(roomId)
                .orElseThrow(() -> new RuntimeException("Room not found with id: " + roomId));

        existing.setRoomNumber(updatedRoom.getRoomNumber());
        existing.setFloorNumber(updatedRoom.getFloorNumber());
        existing.setCapacity(updatedRoom.getCapacity());
        existing.setRoomType(updatedRoom.getRoomType());
        existing.setStatus(updatedRoom.getStatus());

        return roomRepository.save(existing);
    }

    public void deleteRoom(Long roomId) {
        HostelRoom room = roomRepository.findById(roomId)
                .orElseThrow(() -> new RuntimeException("Room not found with id: " + roomId));

        room.setStatus(HostelRoom.RoomStatus.MAINTENANCE);
        roomRepository.save(room);
    }

    // ================= Rooms Allocation=================

    public StudentHostelAllocation allocateRoom(StudentHostelAllocation allocation) {
        allocation.setStatus(StudentHostelAllocation.AllocationStatus.ACTIVE);
        allocation.setJoinDate(LocalDate.now());
        return allocationRepository.save(allocation);
    }

    public List<StudentHostelAllocation> getAllAllocations() {
        return allocationRepository.findAll();
    }

    public StudentHostelAllocation updateAllocation(Long allocationId, StudentHostelAllocation updatedAllocation) {
        StudentHostelAllocation existing = allocationRepository.findById(allocationId)
                .orElseThrow(() -> new RuntimeException("Allocation not found with id: " + allocationId));

        existing.setRoom(updatedAllocation.getRoom());
        
       // existing.setUser(updatedAllocation.getUser());
        existing.setJoinDate(updatedAllocation.getJoinDate());
        existing.setLeaveDate(updatedAllocation.getLeaveDate());
        existing.setStatus(updatedAllocation.getStatus());

        return allocationRepository.save(existing);
    }

    public void deleteAllocation(Long allocationId) {
        StudentHostelAllocation allocation = allocationRepository.findById(allocationId)
                .orElseThrow(() -> new RuntimeException("Allocation not found with id: " + allocationId));

        allocation.setStatus(StudentHostelAllocation.AllocationStatus.CANCELLED);
        allocationRepository.save(allocation);
    }

    // ================= Hostel Fees=================

    public HostelFee addFee(HostelFee fee) {
        fee.setStatus(HostelFee.Status.ACTIVE);
        return feeRepository.save(fee);
    }

    public List<HostelFee> getAllFees() {
        return feeRepository.findAll();
    }

    public HostelFee updateFee(Long feeId, HostelFee updatedFee) {
        HostelFee existing = feeRepository.findById(feeId)
                .orElseThrow(() -> new RuntimeException("Fee not found with id: " + feeId));

        existing.setAmount(updatedFee.getAmount());
        existing.setRoomType(updatedFee.getRoomType());
        existing.setEffectiveFrom(updatedFee.getEffectiveFrom());
        existing.setStatus(updatedFee.getStatus());

        return feeRepository.save(existing);
    }

    public void deleteFee(Long feeId) {
        HostelFee fee = feeRepository.findById(feeId)
                .orElseThrow(() -> new RuntimeException("Fee not found with id: " + feeId));

        fee.setStatus(HostelFee.Status.INACTIVE);
        feeRepository.save(fee);
    }

    // ================= Hostel complaints =================

    public HostelComplaint addComplaint(HostelComplaint complaint) {

        if (complaint.getRoom() == null || complaint.getRoom().getRoomId() == null) {
            throw new RuntimeException("Room ID is required");
        }

        HostelRoom room = roomRepository.findById(
                complaint.getRoom().getRoomId()
        ).orElseThrow(() -> new RuntimeException("Room not found"));

        complaint.setRoom(room);
        complaint.setStatus(HostelComplaint.ComplaintStatus.OPEN);

        return complaintRepository.save(complaint);
    }
    
    public List<HostelComplaint> getAllComplaints() {
        return complaintRepository.findAll();
    }

    public HostelComplaint updateComplaint(Long complaintId, HostelComplaint updatedComplaint) {
        HostelComplaint existing = complaintRepository.findById(complaintId)
                .orElseThrow(() -> new RuntimeException("Complaint not found with id: " + complaintId));

        existing.setRoom(updatedComplaint.getRoom());
      
        //  existing.setUser(updatedComplaint.getUser());
        existing.setComplaintType(updatedComplaint.getComplaintType());
        existing.setDescription(updatedComplaint.getDescription());
        existing.setStatus(updatedComplaint.getStatus());

        return complaintRepository.save(existing);
    }

    public void deleteComplaint(Long complaintId) {
        HostelComplaint complaint = complaintRepository.findById(complaintId)
                .orElseThrow(() -> new RuntimeException("Complaint not found with id: " + complaintId));

        complaint.setStatus(HostelComplaint.ComplaintStatus.RESOLVED);
        complaintRepository.save(complaint);
    }
}
