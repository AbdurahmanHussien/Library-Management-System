package com.springboot.librarysystem.dto;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


@Data
public class BundleMessageDto {

    @JsonProperty("message_ar")
    @Schema(description = "message in arabic", example = "الرسالة بالعربية")
    private String messageAr;

    @JsonProperty("message_en")
    @Schema(description = "message in english", example = "message in english")
    private String messageEn;


    public BundleMessageDto(String messageAr, String messageEn) {
        this.messageAr = messageAr;
        this.messageEn = messageEn;
    }

}
