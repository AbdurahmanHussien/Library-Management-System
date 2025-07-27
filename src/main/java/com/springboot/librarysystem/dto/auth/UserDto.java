package com.springboot.librarysystem.dto.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class UserDto implements Serializable {

	@Schema(description = "User ID", example = "1")
	private Long id;

	@NotBlank(message = "username.is.required")
	@Size(min = 3, max = 20, message = "username.short.or.long")
	@Schema(description = "User name", example = "JohnDoe")
	private String username;

	@NotBlank(message = "email.is.required")
	@Email(message = "invalid.email.format")
	@Schema(description = "User email", example = "5V9YX@example.com")
	private String email;


	@Size(min = 8, max = 20, message = "password.size")
	@Pattern(
			regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]+$",
			message = "password.pattern"
	)
	@Schema(description = "User password", example = "P@ssw0rd")
	private String password;

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	@Schema(description = "User roles", example = "[1, 2]")
	private  Set<Long> roleIds;

	@Schema(description = "User active status", example = "true")
	private boolean isActive;

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	@Schema(description = "User created date", example = "2023-08-01T10:00:00")
	private LocalDateTime createdAt;

}
