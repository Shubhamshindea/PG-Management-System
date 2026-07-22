package com.pgconnect.operations.entity;

import com.pgconnect.pg.entity.PgProperty;
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
 * Entity representing the daily food menu for a PG.
 */
@Entity
@Table(name = "daily_menus")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DailyMenu {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pg_property_id", nullable = false)
    private PgProperty pgProperty;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = true)
    private String breakfastMenu;

    @Column(nullable = true)
    private String lunchMenu;

    @Column(nullable = true)
    private String dinnerMenu;

    @Column(nullable = false)
    private Boolean isBreakfastReady = false;

    @Column(nullable = false)
    private Boolean isLunchReady = false;

    @Column(nullable = false)
    private Boolean isDinnerReady = false;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
