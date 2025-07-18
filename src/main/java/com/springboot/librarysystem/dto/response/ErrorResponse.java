package com.springboot.librarysystem.dto.response;

import com.springboot.librarysystem.dto.BundleMessageDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class ErrorResponse {

    @Schema(description = "HTTP status code", example = "400")
    private int status;
    @Schema(description = "Field name", example = "email")
    private String field;
    @Schema(description = "Error message")
    private BundleMessageDto messages;
    @Schema(description = "Timestamp")
    private LocalDateTime time;


    public ErrorResponse(int status, String field, BundleMessageDto messages ) {
        this.status = status;
        this.field = field;
        this.messages = messages;
        this.time = LocalDateTime.now();
    }
}