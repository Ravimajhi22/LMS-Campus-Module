package com.campusFacilities.www.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.campusFacilities.www.model.Hostel.Hostel;
import com.campusFacilities.www.model.Hostel.HostelBlock;
import com.campusFacilities.www.model.Hostel.HostelComplaint;
import com.campusFacilities.www.model.Hostel.HostelFee;
import com.campusFacilities.www.model.Hostel.HostelRoom;
import com.campusFacilities.www.model.Hostel.StudentHostelAllocation;
import com.campusFacilities.www.service.Imp.HostelServiceImpl;

@RestController
@RequestMapping("/hostel")
public class HostelController {

    @Autowired
    private HostelServiceImpl hostelService;

    // ================= HOSTEL =================
    @PostMapping("/hostels")
    public Hostel addHostel(@RequestBody Hostel hostel) {
        return hostelService.addHostel(hostel);
    }

    @GetMapping("/hostels")
    public List<Hostel> getAllHostels() {
        return hostelService.getAllHostels();
    }

    @PutMapping("/hostels/{id}")
    public Hostel updateHostel(@PathVariable Long id, @RequestBody Hostel hostel) {
        return hostelService.updateHostel(id, hostel);
    }

    @DeleteMapping("/hostels/{id}")
    public void deleteHostel(@PathVariable Long id) {
        hostelService.deleteHostel(id);
    }

    // ================= BLOCK =================
    @PostMapping("/blocks")
    public HostelBlock addBlock(@RequestBody HostelBlock block) {
        return hostelService.addBlock(block);
    }

    @GetMapping("/blocks")
    public List<HostelBlock> getAllBlocks() {
        return hostelService.getAllBlocks();
    }

    @PutMapping("/blocks/{id}")
    public HostelBlock updateBlock(@PathVariable Long id, @RequestBody HostelBlock block) {
        return hostelService.updateBlock(id, block);
    }

    @DeleteMapping("/blocks/{id}")
    public void deleteBlock(@PathVariable Long id) {
        hostelService.deleteBlock(id);
    }

    // ================= ROOM =================
    @PostMapping("/rooms")
    public HostelRoom addRoom(@RequestBody HostelRoom room) {
        return hostelService.addRoom(room);
    }

    @GetMapping("/rooms")
    public List<HostelRoom> getAllRooms() {
        return hostelService.getAllRooms();
    }

    @PutMapping("/rooms/{id}")
    public HostelRoom updateRoom(@PathVariable Long id, @RequestBody HostelRoom room) {
        return hostelService.updateRoom(id, room);
    }

    @DeleteMapping("/rooms/{id}")
    public void deleteRoom(@PathVariable Long id) {
        hostelService.deleteRoom(id);
    }

    // ================= ALLOCATION =================
    @PostMapping("/allocations")
    public StudentHostelAllocation allocateRoom(@RequestBody StudentHostelAllocation allocation) {
        return hostelService.allocateRoom(allocation);
    }

    @GetMapping("/allocations")
    public List<StudentHostelAllocation> getAllAllocations() {
        return hostelService.getAllAllocations();
    }

    @PutMapping("/allocations/{id}")
    public StudentHostelAllocation updateAllocation(@PathVariable Long id, @RequestBody StudentHostelAllocation allocation) {
        return hostelService.updateAllocation(id, allocation);
    }

    @DeleteMapping("/allocations/{id}")
    public void deleteAllocation(@PathVariable Long id) {
        hostelService.deleteAllocation(id);
    }

    // ================= FEES =================
    @PostMapping("/fees")
    public HostelFee addFee(@RequestBody HostelFee fee) {
        return hostelService.addFee(fee);
    }

    @GetMapping("/fees")
    public List<HostelFee> getAllFees() {
        return hostelService.getAllFees();
    }

    @PutMapping("/fees/{id}")
    public HostelFee updateFee(@PathVariable Long id, @RequestBody HostelFee fee) {
        return hostelService.updateFee(id, fee);
    }

    @DeleteMapping("/fees/{id}")
    public void deleteFee(@PathVariable Long id) {
        hostelService.deleteFee(id);
    }

    // ================= COMPLAINTS =================
    
    @PostMapping("/complaints")
    public HostelComplaint addComplaint(@RequestBody HostelComplaint complaint) {
        return hostelService.addComplaint(complaint);
    }

    @GetMapping("/complaints")
    public List<HostelComplaint> getAllComplaints() {
        return hostelService.getAllComplaints();
    }

    @PutMapping("/complaints/{id}")
    public HostelComplaint updateComplaint(@PathVariable Long id, @RequestBody HostelComplaint complaint) {
        return hostelService.updateComplaint(id, complaint);
    }

    @DeleteMapping("/complaints/{id}")
    public void deleteComplaint(@PathVariable Long id) {
        hostelService.deleteComplaint(id);
    }
}
