package com.pgconnect.resident.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

public class ResidentDto {

    @Data
    public static class AddRequest {
        @NotNull(message = "PG Property ID is required")
        private UUID pgPropertyId;

        @NotNull(message = "Bed ID is required")
        private UUID bedId;

        private UUID userId; // Optional: If linking to an existing app user
        
        @NotBlank(message = "Full Name is required")
        private String fullName;

        @NotBlank(message = "Emergency Contact is required")
        private String emergencyContact;

        @NotNull(message = "Agreed Rent is required")
        private Double agreedRent;

        @NotNull(message = "Move-in Date is required")
        private LocalDate moveInDate;
    }

    @Data
    public static class Response {
        private UUID id;
        private UUID userId;
        private UUID pgPropertyId;
        private UUID bedId;
        private String fullName;
        private String emergencyContact;
        private Double agreedRent;
        private LocalDate moveInDate;
        private LocalDate moveOutDate;
    }
}
