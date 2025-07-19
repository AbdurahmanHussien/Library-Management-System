package com.springboot.librarysystem.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class PublisherDto implements Serializable {

	@Schema(description = "publisher.id", example = "1")
	private Long id;

	@NotBlank(message = "name.is.required")
	@Size(min = 3, max = 20, message = "name.short.or.long")
	@Schema(description = "publisher.name", example = "Penguin Random House")
	private String name;

	@NotBlank(message = "address.is.required")
	@Schema(description = "publisher.address", example = "New York, USA")
	private String address;

	@NotBlank(message = "contact.is.required")
	@Email(message = "invalid.email.format")
	@Schema(description = "publisher.contact.email", example = "9H4o5@example.com")
	private String contactEmail;
}
