package com.springboot.librarysystem.dto.auth;

import lombok.*;

import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class UserDto {
	private Long id;
	private String username;
	private String email;
	private String password;
	private Set<Long> roleIds;
	private boolean isActive;

}
