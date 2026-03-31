package com.campusFacilities.www.repository.Transport;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.campusFacilities.www.model.Transport.TransportPayments;

@Repository
public interface TransportPaymentRepository 
extends JpaRepository<TransportPayments, String> {

List<TransportPayments> findByStudentId(Long studentId);
}