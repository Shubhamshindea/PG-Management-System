package com.pgconnect.pg.entity;

import com.pgconnect.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Entity representing a PG Property.
 */
@Entity
@Table(name = "pg_properties")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PgProperty {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    // Multi-tenancy: Which owner owns this PG
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String state;

    @Column(nullable = false)
    private String pincode;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
