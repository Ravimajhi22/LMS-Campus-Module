package com.campusFacilities.www.repository.Hostel;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.campusFacilities.www.model.Hostel.MessDayMenu;

@Repository
public interface MessDayMenuRepository extends JpaRepository<MessDayMenu, Long> {

    Optional<MessDayMenu> findByDay(MessDayMenu.DayOfWeek day);
}