package com.plivo.assignment.dto;

import java.util.*;

import lombok.*;

@Data
public class ErrorResponse {

    private List<AppResponse> errors;

    @Data
    public static class AppResponse {
        private String message;
        private String error;
    }
}
