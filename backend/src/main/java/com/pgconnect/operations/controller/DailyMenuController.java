package com.pgconnect.operations.controller;

import com.pgconnect.operations.dto.DailyMenuDto;
import com.pgconnect.operations.service.DailyMenuService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.UUID;

/**
 * REST Controller for managing PG Food Operations.
 */
@RestController
@RequestMapping("/api/v1/operations/food")
@RequiredArgsConstructor
@CrossOrigin(origins = "*") // Allow frontend to connect
public class DailyMenuController {

    private final DailyMenuService dailyMenuService;

    @PostMapping("/menu")
    public ResponseEntity<DailyMenuDto.Response> saveMenu(
            @Valid @RequestBody DailyMenuDto.Request request) {
        
        DailyMenuDto.Response response = dailyMenuService.saveMenu(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/menu/{pgPropertyId}")
    public ResponseEntity<DailyMenuDto.Response> getMenuByDate(
            @PathVariable("pgPropertyId") UUID pgPropertyId,
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        
        DailyMenuDto.Response response = dailyMenuService.getMenuByDate(pgPropertyId, date);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/menu/{menuId}/status")
    public ResponseEntity<DailyMenuDto.Response> updateStatus(
            @PathVariable("menuId") UUID menuId,
            @RequestBody DailyMenuDto.StatusUpdate status) {
        
        DailyMenuDto.Response response = dailyMenuService.updateStatus(menuId, status);
        return ResponseEntity.ok(response);
    }
}
