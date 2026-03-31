package com.campusFacilities.www.repository.Transport;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.campusFacilities.www.model.Transport.TransportSetting;

@Repository
public interface TransportSettingRepository 
        extends JpaRepository<TransportSetting, Long> {

    Optional<TransportSetting> findByKeyName(String keyName);
}
