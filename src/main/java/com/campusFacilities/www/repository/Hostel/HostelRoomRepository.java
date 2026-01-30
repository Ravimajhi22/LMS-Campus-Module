package com.campusFacilities.www.repository.Hostel;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.campusFacilities.www.model.Hostel.HostelRoom;

@Repository
public interface HostelRoomRepository extends JpaRepository<HostelRoom, Long> {

    List<HostelRoom> findByIsDeletedFalse();
}