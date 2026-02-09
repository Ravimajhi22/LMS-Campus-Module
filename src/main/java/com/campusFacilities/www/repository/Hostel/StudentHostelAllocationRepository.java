package com.campusFacilities.www.repository.Hostel;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.campusFacilities.www.model.Hostel.StudentHostelAllocation;

@Repository
public interface StudentHostelAllocationRepository
        extends JpaRepository<StudentHostelAllocation, Long> {

    List<StudentHostelAllocation> findByStatus(
            StudentHostelAllocation.AllocationStatus status
    );

    Optional<StudentHostelAllocation>
    findTopByStudentIdOrderByAllocationIdDesc(Long studentId);

    Optional<StudentHostelAllocation>
    findByStudentIdAndStatus(
            Long studentId,
            StudentHostelAllocation.AllocationStatus status
    );
}
