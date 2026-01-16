package com.campusFacilities.www.repository.Transport;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.campusFacilities.www.model.Transport.ConductorDetails;
import com.campusFacilities.www.model.Transport.ConductorDetails.ConductorVerificationStatus;

@Repository
public interface ConductorDetailsRepository
        extends JpaRepository<ConductorDetails, Long> {

    List<ConductorDetails> findByActiveTrue();

    List<ConductorDetails> findByVerificationStatus(
            ConductorVerificationStatus status);

    List<ConductorDetails> findByVehicle_VechicleNumber(String vechicleNumber);

    List<ConductorDetails> findByRoute_RouteCode(Long routeCode);
}