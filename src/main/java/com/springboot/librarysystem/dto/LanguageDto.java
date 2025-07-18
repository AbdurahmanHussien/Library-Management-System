package com.springboot.librarysystem.dto;

import com.springboot.librarysystem.constants.Languages;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class LanguageDto {

	@Schema(description = "Language ID", example = "1")
    private Long id;

	@NotNull(message = "name.is.required")
	@Schema(description = "Language name", example = "EN")
	private Languages name;
}
