package com.springboot.librarysystem.controller;

import com.springboot.librarysystem.config.UserActivityLogger;
import com.springboot.librarysystem.dto.auth.UserDto;
import com.springboot.librarysystem.dto.response.ErrorResponse;
import com.springboot.librarysystem.service.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

	private final IUserService userService;
	private final UserActivityLogger userActivityLogger;

	@GetMapping
	@Operation(summary = "Get all users")
	@ApiResponses({
			@ApiResponse(
					responseCode = "200",
					description = "Get all users",
					content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserDto.class))
			),
			@ApiResponse(
					responseCode = "400",
					description = "Bad request",
					content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
			)

	})
	public ResponseEntity<List<UserDto>> getAllUsers() {
		List<UserDto> users = userService.getAllUsers();
		userActivityLogger.logUserAction("Get","All users");
		return ResponseEntity.ok().body(users);
	}


	@GetMapping("/{id}")
	@Operation(summary = "Get user by id")
	@ApiResponses({
			@ApiResponse(
					responseCode = "200",
					description = "Get user by id",
					content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserDto.class))
			),
			@ApiResponse(
					responseCode = "400",
					description = "Bad request",
					content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
			)
	})
	public ResponseEntity<Optional<UserDto>> getUserById(@PathVariable Long id) {
		Optional<UserDto> user = userService.getUserById(id);
		userActivityLogger.logUserAction("Get","User by id: " + id);
		return ResponseEntity.ok().body(user);
	}

	@PostMapping
	@Operation(summary = "Create user")
	@ApiResponses({
			@ApiResponse(
					responseCode = "200",
					description = "create user",
					content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserDto.class))
			),
			@ApiResponse(
					responseCode = "400",
					description = "Bad request",
					content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
			)
	})
	public ResponseEntity<UserDto> createUser( @Valid @RequestBody UserDto userDto, @RequestParam List<Long> roleIds) {
		UserDto user = userService.createUser(userDto, roleIds);
		userActivityLogger.logUserAction("Post","Create user");
		return ResponseEntity.ok().body(user);
	}

	@PutMapping
	@Operation(summary = "Update user")
	@ApiResponses({
			@ApiResponse(
					responseCode = "200",
					description = "update user",
					content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserDto.class))
			),
			@ApiResponse(
					responseCode = "400",
					description = "Bad request",
					content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
			)
	})
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @RequestParam List<Long> roleIds) {
		UserDto user = userService.updateUser(userDto, roleIds);
		userActivityLogger.logUserAction("Put","Update user with id: " + userDto.getId());
		return ResponseEntity.ok().body(user);
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Delete user")
	@ApiResponses({
			@ApiResponse(
					responseCode = "200",
					description = "delete user",
					content = @Content(mediaType = "text/plain", schema = @Schema(type = "string", example = "User deleted successfully"))
			),
			@ApiResponse(
					responseCode = "400",
					description = "Bad request",
					content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
			)
	})
	public ResponseEntity<?> deleteUser(@PathVariable Long id) {
		userService.deleteUser(id);
		userActivityLogger.logUserAction("Delete","Delete user with id: " + id);
		return ResponseEntity.ok("User deleted successfully");
	}
}
