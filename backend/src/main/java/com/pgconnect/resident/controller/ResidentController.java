package com.pgconnect.resident.controller;

import com.pgconnect.resident.dto.ResidentDto;
import com.pgconnect.resident.service.ResidentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * REST Controller for managing Residents.
 */
@RestController
@RequestMapping("/api/v1/residents")
@RequiredArgsConstructor
@CrossOrigin(origins = "*") // Allow frontend to connect
public class ResidentController {

    private final ResidentService residentService;

    @PostMapping
    public ResponseEntity<ResidentDto.Response> addResident(
            @Valid @RequestBody ResidentDto.AddRequest request) {
        
        ResidentDto.Response response = residentService.addResident(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/property/{pgPropertyId}")
    public ResponseEntity<List<ResidentDto.Response>> getResidentsByPg(
            @PathVariable("pgPropertyId") UUID pgPropertyId) {
        
        List<ResidentDto.Response> response = residentService.getResidentsByPg(pgPropertyId);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{residentId}")
    public ResponseEntity<Void> removeResident(
            @PathVariable("residentId") UUID residentId) {
        
        residentService.removeResident(residentId);
        return ResponseEntity.noContent().build();
    }
}
