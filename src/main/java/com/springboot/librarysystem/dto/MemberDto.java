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

	@NotBlank(message = "name.is.required")
	@Size(min = 3, max = 20, message = "name.short.or.long")
	private String name;

	@NotBlank(message = "email.is.required")
	@Email(message = "invalid.email.format")
	private String email;

	@NotBlank(message = "phone.is.required")
	@Pattern(regexp = "^01[0125]\\d{8}$", message = "invalid.phone.format")
	private String phone;

	@NotBlank(message = "address.is.required")
	private String address;
}

