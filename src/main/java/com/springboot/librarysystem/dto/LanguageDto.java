package com.springboot.librarysystem.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class LanguageDto {

    private Long id;
	@NotBlank(message = "Name is required")
	@Size(min = 3, max = 10, message = "Name must be between 2 and 10 characters")
	private	String name;
}
