package com.campusFacilities.www.repository.Library;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.campusFacilities.www.model.Library.LibraryMember;

public interface LibraryMemberRepository extends JpaRepository<LibraryMember, Long> {
	
	List<LibraryMember> findByIsDeletedFalse();
}
