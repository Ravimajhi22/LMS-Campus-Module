package com.campusFacilities.www.repository.Library;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.campusFacilities.www.model.Library.FineSlab;


@Repository
public interface FineSlabRepository extends JpaRepository<FineSlab, Long> {

    List<FineSlab> findByLibrarySettingsSettingIdOrderByFromDayAsc(Long settingId);

    void deleteByLibrarySettingsSettingId(Long settingId);
}

