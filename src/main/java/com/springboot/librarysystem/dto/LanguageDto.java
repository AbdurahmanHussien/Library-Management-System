package com.springboot.librarysystem.dto;

import com.springboot.librarysystem.constants.Languages;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class LanguageDto {

    private Long id;

	@NotNull(message = "name.is.required")
	private Languages name;
}
