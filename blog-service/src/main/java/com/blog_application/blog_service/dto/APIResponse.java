package com.blog_application.blog_service.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class APIResponse {
    private String message;
    private boolean success;
    public APIResponse(boolean success, String message) {
        this.success = success;

        this.message = message;
    }
}
