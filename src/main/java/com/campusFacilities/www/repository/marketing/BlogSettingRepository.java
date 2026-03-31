package com.campusFacilities.www.repository.marketing;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.campusFacilities.www.model.marketing.BlogSetting;

@Repository
public interface BlogSettingRepository 
        extends JpaRepository<BlogSetting, Long> {

	BlogSetting save(BlogSetting setting);

}