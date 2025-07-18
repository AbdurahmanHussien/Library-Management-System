package com.springboot.librarysystem.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class AuthorDto {

    private Long id;

	@NotBlank(message = "Name is required")
	@Size(min = 3, max = 10, message = "Name must be between 3 and 10 characters")
	private String name;

	@Size(max = 200, message = "Bio must not exceed 200 characters")
	private String bio;

}
