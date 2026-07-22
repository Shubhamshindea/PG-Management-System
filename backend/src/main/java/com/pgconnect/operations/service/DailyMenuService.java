package com.pgconnect.operations.service;

import com.pgconnect.operations.dto.DailyMenuDto;

import java.time.LocalDate;
import java.util.UUID;

public interface DailyMenuService {
    DailyMenuDto.Response saveMenu(DailyMenuDto.Request request);
    DailyMenuDto.Response getMenuByDate(UUID pgPropertyId, LocalDate date);
    DailyMenuDto.Response updateStatus(UUID menuId, DailyMenuDto.StatusUpdate status);
}
