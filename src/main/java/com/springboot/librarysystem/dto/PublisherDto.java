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

	@NotBlank(message = "name.is.required")
	@Size(min = 3, max = 20, message = "name.short.or.long")
	private String name;

	@NotBlank(message = "address.is.required")
	private String address;

	@NotBlank(message = "contact.is.required")
	@Email(message = "invalid.email.format")
	private String contactEmail;
}
