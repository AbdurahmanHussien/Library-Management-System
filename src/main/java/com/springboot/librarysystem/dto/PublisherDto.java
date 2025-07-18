package com.springboot.librarysystem.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class PublisherDto {

	private Long id;

	@NotBlank(message = "Name is required")
	@Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
	private String name;

	@NotBlank(message = "Address is required")
	@Size(min = 5, max = 200, message = "Address must be between 5 and 200 characters")
	private String address;

	@NotBlank(message = "Contact email is required")
	@Email(message = "Invalid email format")
	private String contactEmail;
}
