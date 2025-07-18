package com.springboot.librarysystem.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class MemberDto {

	@Schema(description = "Member ID", example = "1")
	private Long id;

	@NotBlank(message = "name.is.required")
	@Size(min = 3, max = 20, message = "name.short.or.long")
	@Schema(description = "Member name", example = "John Doe")
	private String name;

	@NotBlank(message = "email.is.required")
	@Email(message = "invalid.email.format")
	@Schema(description = "Member email", example = "5V9YX@example.com")
	private String email;

	@NotBlank(message = "phone.is.required")
	@Pattern(regexp = "^01[0125]\\d{8}$", message = "invalid.phone.format")
	@Schema(description = "Member phone number", example = "01012345678")
	private String phone;

	@NotBlank(message = "address.is.required")
	@Schema(description = "Member address", example = "123 Main Street, New York, USA")
	private String address;
}

