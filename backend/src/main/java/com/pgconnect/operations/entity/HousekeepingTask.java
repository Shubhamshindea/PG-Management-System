package com.pgconnect.operations.entity;

import com.pgconnect.pg.entity.PgProperty;
import com.pgconnect.pg.entity.Room;
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
 * Entity representing a Housekeeping task.
 */
@Entity
@Table(name = "housekeeping_tasks")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HousekeepingTask {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pg_property_id", nullable = false)
    private PgProperty pgProperty;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assigned_staff_id", nullable = true)
    private User assignedStaff; // Must have role STAFF

    @Column(nullable = false)
    private LocalDate scheduledDate;

    @Column(nullable = false)
    private String status; // PENDING, COMPLETED, ROOM_LOCKED

    @Column(nullable = true)
    private String notes;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
