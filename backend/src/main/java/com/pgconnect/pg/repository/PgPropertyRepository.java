package com.pgconnect.pg.repository;

import com.pgconnect.pg.entity.PgProperty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PgPropertyRepository extends JpaRepository<PgProperty, UUID> {
    List<PgProperty> findByOwnerId(UUID ownerId);
}
