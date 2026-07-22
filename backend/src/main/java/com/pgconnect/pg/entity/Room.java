package com.pgconnect.pg.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * Entity representing a Room in a PG Property.
 */
@Entity
@Table(name = "rooms")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pg_property_id", nullable = false)
    private PgProperty pgProperty;

    @Column(nullable = false)
    private String roomNumber;

    @Column(nullable = false)
    private Integer capacity; // Number of beds

    @Column(nullable = false)
    private String roomType; // e.g. "AC", "NON_AC"

    @Column(nullable = false)
    private Double baseRent;
}
