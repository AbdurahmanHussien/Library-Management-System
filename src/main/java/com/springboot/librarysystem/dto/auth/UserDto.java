package com.springboot.librarysystem.dto.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.springboot.librarysystem.entity.auth.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class UserDto {

	private Long id;

	@NotBlank(message = "username.is.required")
	@Size(min = 3, max = 20, message = "username.short.or.long")
	private String username;

	@NotBlank(message = "email.is.required")
	@Email(message = "invalid.email.format")
	private String email;


	@Size(min = 8, max = 20, message = "password.size")
	@Pattern(
			regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]+$",
			message = "password.pattern"
	)
	private String password;

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private  Set<Long> roleIds;

	private boolean isActive;

}
