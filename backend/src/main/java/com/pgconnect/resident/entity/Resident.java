package com.pgconnect.resident.entity;

import com.pgconnect.pg.entity.Bed;
import com.pgconnect.pg.entity.PgProperty;
import com.pgconnect.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Entity representing a Resident living in a PG.
 */
@Entity
@Table(name = "residents")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Resident {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    // A resident may also be a registered User (to use the app)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = true)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pg_property_id", nullable = false)
    private PgProperty pgProperty;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bed_id", nullable = false)
    private Bed bed;

    @Column(nullable = false)
    private String emergencyContact;

    @Column(nullable = true)
    private String idProofUrl; // URL to uploaded document

    @Column(nullable = false)
    private Double agreedRent;

    @Column(nullable = false)
    private LocalDate moveInDate;

    @Column(nullable = true)
    private LocalDate moveOutDate;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
