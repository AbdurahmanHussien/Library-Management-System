package com.springboot.librarysystem.dto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


@Data
public class BundleMessageDto {

    @JsonProperty("message_ar")
    private String messageAr;

    @JsonProperty("message_en")
    private String messageEn;


    public BundleMessageDto(String messageAr, String messageEn) {
        this.messageAr = messageAr;
        this.messageEn = messageEn;
    }

}
