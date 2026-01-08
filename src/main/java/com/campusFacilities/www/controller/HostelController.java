package com.campusFacilities.www.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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
    @PreAuthorize("hasAuthority('HOSTEL_CREATE')")
    public Hostel addHostel(@RequestBody Hostel hostel) {
        return hostelService.addHostel(hostel);
    }
    
    @GetMapping("/hostels")
    @PreAuthorize("hasAuthority('HOSTEL_VIEW')")
    public List<Hostel> getAllHostels() {
        return hostelService.getAllHostels();
    }


    @PutMapping("/hostels/{id}")
    @PreAuthorize("hasAuthority('HOSTEL_UPDATE')")
    public Hostel updateHostel(@PathVariable Long id, @RequestBody Hostel hostel) {
        return hostelService.updateHostel(id, hostel);
    }

    @DeleteMapping("/hostels/{id}")
    @PreAuthorize("hasAuthority('HOSTEL_DELETE')")
    public void deleteHostel(@PathVariable Long id) {
        hostelService.deleteHostel(id);
    }
    
    @PatchMapping("/hostels/{id}")
    @PreAuthorize("hasAuthority('HOSTEL_UPDATE')")
    public Hostel patchHostel(@PathVariable Long id, @RequestBody Hostel hostel) {
        return hostelService.patchHostel(id, hostel);
    }
    
    // ================= BLOCK ====================//
    
    
    
    @PostMapping("/blocks")
    @PreAuthorize("hasAuthority('HOSTEL_BLOCK_CREATE')")
    public HostelBlock addBlock(@RequestBody HostelBlock block) {
        return hostelService.addBlock(block);
    }
    
    @GetMapping("/blocks")
    @PreAuthorize("hasAuthority('HOSTEL_BLOCK_VIEW')")
    public List<HostelBlock> getAllBlocks() {
        return hostelService.getAllBlocks();
    }

    @PutMapping("/blocks/{id}")
    @PreAuthorize("hasAuthority('HOSTEL_BLOCK_UPDATE')")
    public HostelBlock updateBlock(@PathVariable Long id, @RequestBody HostelBlock block) {
        return hostelService.updateBlock(id, block);
    }

    @DeleteMapping("/blocks/{id}")
    @PreAuthorize("hasAuthority('HOSTEL_BLOCK_DELETE')")
    public void deleteBlock(@PathVariable Long id) {
        hostelService.deleteBlock(id);
    }
    
    @PatchMapping("/blocks/{id}")
    @PreAuthorize("hasAuthority('HOSTEL_BLOCK_UPDATE')")
    public HostelBlock patchBlock(@PathVariable Long id, @RequestBody HostelBlock block) {
        return hostelService.patchBlock(id, block);
    }
    // ================= ROOM ====================//
        
        @PostMapping("/rooms")
        @PreAuthorize("hasAuthority('HOSTEL_ROOM_CREATE')")
        public HostelRoom addRoom(@RequestBody HostelRoom room) {
            return hostelService.addRoom(room);
        }
        
        @GetMapping("/rooms")
        @PreAuthorize("hasAuthority('HOSTEL_ROOM_VIEW')")
        public List<HostelRoom> getAllRooms() {
            return hostelService.getAllRooms();
        }

        @PutMapping("/rooms/{id}")
        @PreAuthorize("hasAuthority('HOSTEL_ROOM_UPDATE')")
        public HostelRoom updateRoom(@PathVariable Long id, @RequestBody HostelRoom room) {
            return hostelService.updateRoom(id, room);
        }


        @DeleteMapping("/rooms/{id}")
        @PreAuthorize("hasAuthority('HOSTEL_ROOM_DELETE')")
        public void deleteRoom(@PathVariable Long id) {
            hostelService.deleteRoom(id);
        }
        
        @PatchMapping("/rooms/{id}")
        @PreAuthorize("hasAuthority('HOSTEL_ROOM_UPDATE')")
        public HostelRoom patchRoom(@PathVariable Long id, @RequestBody HostelRoom room) {
            return hostelService.patchRoom(id, room);
        }
        
    // ================= ALLOCATION ====================//
        
        
        @PostMapping("/allocations")
        @PreAuthorize("hasAuthority('HOSTEL_ALLOCATION_CREATE')")
        public StudentHostelAllocation allocateRoom(@RequestBody StudentHostelAllocation allocation) {
            return hostelService.allocateRoom(allocation);
        }
        
        @GetMapping("/allocations")
        @PreAuthorize("hasAuthority('HOSTEL_ALLOCATION_VIEW')")
        public List<StudentHostelAllocation> getAllAllocations() {
            return hostelService.getAllAllocations();
        }

        @PutMapping("/allocations/{id}")
        @PreAuthorize("hasAuthority('HOSTEL_ALLOCATION_UPDATE')")
        public StudentHostelAllocation updateAllocation(
                @PathVariable Long id,
                @RequestBody StudentHostelAllocation allocation) {
            return hostelService.updateAllocation(id, allocation);
        }

        @DeleteMapping("/allocations/{id}")
        @PreAuthorize("hasAuthority('HOSTEL_ALLOCATION_DELETE')")
        public void deleteAllocation(@PathVariable Long id) {
            hostelService.deleteAllocation(id);
        }
        @PatchMapping("/allocations/{id}")
        @PreAuthorize("hasAuthority('HOSTEL_ALLOCATION_UPDATE')")
        public StudentHostelAllocation patchAllocation(
                @PathVariable Long id,
                @RequestBody StudentHostelAllocation allocation) {
            return hostelService.patchAllocation(id, allocation);
        }
        
    // ================= FEES ======================//
        
        
        
        @PostMapping("/fees")
        @PreAuthorize("hasAuthority('HOSTEL_FEE_CREATE')")
        public HostelFee addFee(@RequestBody HostelFee fee) {
            return hostelService.addFee(fee);
        }

        @GetMapping("/fees")
        @PreAuthorize("hasAuthority('HOSTEL_FEE_VIEW')")
        public List<HostelFee> getAllFees() {
            return hostelService.getAllFees();
        }


        @PutMapping("/fees/{id}")
        @PreAuthorize("hasAuthority('HOSTEL_FEE_UPDATE')")
        public HostelFee updateFee(@PathVariable Long id, @RequestBody HostelFee fee) {
            return hostelService.updateFee(id, fee);
        }



        @DeleteMapping("/fees/{id}")
        @PreAuthorize("hasAuthority('HOSTEL_FEE_DELETE')")
        public void deleteFee(@PathVariable Long id) {
            hostelService.deleteFee(id);
        }
        
        @PatchMapping("/fees/{id}")
        @PreAuthorize("hasAuthority('HOSTEL_FEE_UPDATE')")
        public HostelFee patchFee(@PathVariable Long id, @RequestBody HostelFee fee) {
            return hostelService.patchFee(id, fee);
        }

    // ================= COMPLAINTS ====================//
    
        @PostMapping("/complaints")
        @PreAuthorize("hasAuthority('HOSTEL_COMPLAINT_CREATE')")
        public HostelComplaint addComplaint(@RequestBody HostelComplaint complaint) {
            return hostelService.addComplaint(complaint);
        }


        @GetMapping("/complaints")
        @PreAuthorize("hasAuthority('HOSTEL_COMPLAINT_VIEW')")
        public List<HostelComplaint> getAllComplaints() {
            return hostelService.getAllComplaints();
        }

        @PutMapping("/complaints/{id}")
        @PreAuthorize("hasAuthority('HOSTEL_COMPLAINT_UPDATE')")
        public HostelComplaint updateComplaint(
                @PathVariable Long id,
                @RequestBody HostelComplaint complaint) {
            return hostelService.updateComplaint(id, complaint);
        }

        @DeleteMapping("/complaints/{id}")
        @PreAuthorize("hasAuthority('HOSTEL_COMPLAINT_DELETE')")
        public void deleteComplaint(@PathVariable Long id) {
            hostelService.deleteComplaint(id);
        }
            
         @PatchMapping("/complaints/{id}")
         @PreAuthorize("hasAuthority('HOSTEL_COMPLAINT_UPDATE')")
            public HostelComplaint patchComplaint(
                    @PathVariable Long id,
                    @RequestBody HostelComplaint complaint) {
                return hostelService.patchComplaint(id, complaint);
    }
}
