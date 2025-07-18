package com.springboot.librarysystem.dto;

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
	private Long id;

	@NotBlank(message = "Name is required")
	@Size(min = 3, max = 20, message = "Name must be between 3 and 20 characters")
	private String name;

	@NotBlank(message = "Email is required")
	@Email(message = "Invalid email format")
	private String email;

	@NotBlank(message = "Phone number is required")
	@Pattern(regexp = "^01[0125]\\d{8}$", message = "Phone number must be a valid Egyptian number")
	private String phone;

	@NotBlank(message = "Address is required")
	@Size(min = 5, max = 200, message = "Address must be between 5 and 200 characters")
	private String address;
}

