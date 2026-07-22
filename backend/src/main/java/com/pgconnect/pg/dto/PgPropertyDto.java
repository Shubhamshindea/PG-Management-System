package com.pgconnect.pg.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.UUID;

public class PgPropertyDto {

    @Data
    public static class Request {
        @NotBlank(message = "Name is required")
        private String name;

        @NotBlank(message = "Address is required")
        private String address;

        @NotBlank(message = "City is required")
        private String city;

        @NotBlank(message = "State is required")
        private String state;

        @NotBlank(message = "Pincode is required")
        private String pincode;
    }

    @Data
    public static class Response {
        private UUID id;
        private String name;
        private String address;
        private String city;
        private String state;
        private String pincode;
    }
}
