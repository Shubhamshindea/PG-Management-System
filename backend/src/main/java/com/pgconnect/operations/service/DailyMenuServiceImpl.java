package com.pgconnect.operations.service;

import com.pgconnect.operations.dto.DailyMenuDto;
import com.pgconnect.operations.entity.DailyMenu;
import com.pgconnect.operations.repository.DailyMenuRepository;
import com.pgconnect.pg.entity.PgProperty;
import com.pgconnect.pg.repository.PgPropertyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DailyMenuServiceImpl implements DailyMenuService {

    private final DailyMenuRepository dailyMenuRepository;
    private final PgPropertyRepository pgPropertyRepository;

    @Override
    public DailyMenuDto.Response saveMenu(DailyMenuDto.Request request) {
        PgProperty property = pgPropertyRepository.findById(request.getPgPropertyId())
                .orElseThrow(() -> new RuntimeException("PG Property not found"));

        // Check if menu exists for this date, if so update it
        Optional<DailyMenu> existing = dailyMenuRepository.findByPgPropertyIdAndDate(
                request.getPgPropertyId(), request.getDate());

        DailyMenu menu = existing.orElseGet(() -> DailyMenu.builder()
                .pgProperty(property)
                .date(request.getDate())
                .isBreakfastReady(false)
                .isLunchReady(false)
                .isDinnerReady(false)
                .build());

        menu.setBreakfastMenu(request.getBreakfastMenu());
        menu.setLunchMenu(request.getLunchMenu());
        menu.setDinnerMenu(request.getDinnerMenu());

        menu = dailyMenuRepository.save(menu);
        return mapToResponse(menu);
    }

    @Override
    public DailyMenuDto.Response getMenuByDate(UUID pgPropertyId, LocalDate date) {
        DailyMenu menu = dailyMenuRepository.findByPgPropertyIdAndDate(pgPropertyId, date)
                .orElseThrow(() -> new RuntimeException("Menu not set for this date"));
        return mapToResponse(menu);
    }

    @Override
    public DailyMenuDto.Response updateStatus(UUID menuId, DailyMenuDto.StatusUpdate status) {
        DailyMenu menu = dailyMenuRepository.findById(menuId)
                .orElseThrow(() -> new RuntimeException("Menu not found"));

        if (status.getIsBreakfastReady() != null) menu.setIsBreakfastReady(status.getIsBreakfastReady());
        if (status.getIsLunchReady() != null) menu.setIsLunchReady(status.getIsLunchReady());
        if (status.getIsDinnerReady() != null) menu.setIsDinnerReady(status.getIsDinnerReady());

        // In a real application, triggering push notifications to residents would happen here.

        menu = dailyMenuRepository.save(menu);
        return mapToResponse(menu);
    }

    private DailyMenuDto.Response mapToResponse(DailyMenu menu) {
        DailyMenuDto.Response response = new DailyMenuDto.Response();
        response.setId(menu.getId());
        response.setPgPropertyId(menu.getPgProperty().getId());
        response.setDate(menu.getDate());
        response.setBreakfastMenu(menu.getBreakfastMenu());
        response.setLunchMenu(menu.getLunchMenu());
        response.setDinnerMenu(menu.getDinnerMenu());
        response.setIsBreakfastReady(menu.getIsBreakfastReady());
        response.setIsLunchReady(menu.getIsLunchReady());
        response.setIsDinnerReady(menu.getIsDinnerReady());
        return response;
    }
}
