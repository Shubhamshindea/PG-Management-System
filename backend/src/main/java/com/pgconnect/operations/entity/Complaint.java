package com.pgconnect.operations.entity;

import com.pgconnect.pg.entity.PgProperty;
import com.pgconnect.resident.entity.Resident;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Entity representing a Resident Complaint/Ticket.
 */
@Entity
@Table(name = "complaints")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Complaint {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pg_property_id", nullable = false)
    private PgProperty pgProperty;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resident_id", nullable = false)
    private Resident resident;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, length = 1000)
    private String description;

    @Column(nullable = false)
    private String category; // e.g., PLUMBING, ELECTRICAL, FOOD, HYGIENE, OTHER

    @Column(nullable = false)
    private String status; // OPEN, IN_PROGRESS, RESOLVED

    @CreationTimestamp
    private LocalDateTime createdAt;
    
    @Column(nullable = true)
    private LocalDateTime resolvedAt;
}
