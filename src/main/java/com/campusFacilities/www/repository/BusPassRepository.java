package com.campusFacilities.www.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.campusFacilities.www.model.Transport.BusPass;

public interface BusPassRepository extends JpaRepository<BusPass ,Long>
{

}
