package com.pgconnect.operations.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

public class DailyMenuDto {

    @Data
    public static class Request {
        @NotNull(message = "PG Property ID is required")
        private UUID pgPropertyId;
        
        @NotNull(message = "Date is required")
        private LocalDate date;

        private String breakfastMenu;
        private String lunchMenu;
        private String dinnerMenu;
    }

    @Data
    public static class StatusUpdate {
        private Boolean isBreakfastReady;
        private Boolean isLunchReady;
        private Boolean isDinnerReady;
    }

    @Data
    public static class Response {
        private UUID id;
        private UUID pgPropertyId;
        private LocalDate date;
        private String breakfastMenu;
        private String lunchMenu;
        private String dinnerMenu;
        private Boolean isBreakfastReady;
        private Boolean isLunchReady;
        private Boolean isDinnerReady;
    }
}
