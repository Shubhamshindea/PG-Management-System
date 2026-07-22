package com.pgconnect.resident.repository;

import com.pgconnect.resident.entity.Resident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ResidentRepository extends JpaRepository<Resident, UUID> {
    List<Resident> findByPgPropertyId(UUID pgPropertyId);
    List<Resident> findByUserId(UUID userId);
}
