package com.ironhack.usercatalog.repository;

import com.ironhack.usercatalog.model.PlatformData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlatformRepository extends JpaRepository<PlatformData, Integer> {
    PlatformData findByShortName(String shortName);
}
