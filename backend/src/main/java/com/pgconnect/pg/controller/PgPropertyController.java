package com.pgconnect.pg.controller;

import com.pgconnect.pg.dto.PgPropertyDto;
import com.pgconnect.pg.service.PgPropertyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * REST Controller for managing PG Properties.
 */
@RestController
@RequestMapping("/api/v1/pg-properties")
@RequiredArgsConstructor
@CrossOrigin(origins = "*") // Allow frontend to connect
public class PgPropertyController {

    private final PgPropertyService pgPropertyService;

    // In a real app, ownerId would come from the JWT token SecurityContext.
    // For now, we simulate it via a custom header.
    @PostMapping
    public ResponseEntity<PgPropertyDto.Response> createPg(
            @Valid @RequestBody PgPropertyDto.Request request,
            @RequestHeader("X-Owner-Id") UUID ownerId) {
        
        PgPropertyDto.Response response = pgPropertyService.createPg(request, ownerId);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PgPropertyDto.Response>> getAllPgsForOwner(
            @RequestHeader("X-Owner-Id") UUID ownerId) {
        
        List<PgPropertyDto.Response> response = pgPropertyService.getAllPgsForOwner(ownerId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PgPropertyDto.Response> getPgById(
            @PathVariable("id") UUID pgId,
            @RequestHeader("X-Owner-Id") UUID ownerId) {
        
        PgPropertyDto.Response response = pgPropertyService.getPgById(pgId, ownerId);
        return ResponseEntity.ok(response);
    }
}
