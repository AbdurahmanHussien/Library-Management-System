package com.springboot.librarysystem.dto.response;

import com.springboot.librarysystem.dto.BundleMessageDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class ErrorResponse {

    private int status;
    private String field;
    private BundleMessageDto messages;
    private LocalDateTime time;


    public ErrorResponse(int status, String field, BundleMessageDto messages ) {
        this.status = status;
        this.field = field;
        this.messages = messages;
        this.time = LocalDateTime.now();
    }
}