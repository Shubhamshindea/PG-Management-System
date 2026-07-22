package com.pgconnect.operations.repository;

import com.pgconnect.operations.entity.DailyMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface DailyMenuRepository extends JpaRepository<DailyMenu, UUID> {
    Optional<DailyMenu> findByPgPropertyIdAndDate(UUID pgPropertyId, LocalDate date);
}
