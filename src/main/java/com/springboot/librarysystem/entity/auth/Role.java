package com.springboot.librarysystem.entity.auth;

import com.springboot.librarysystem.constants.RoleNames;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "library_roles")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	@Column(unique = true, nullable = false)
	@Enumerated(EnumType.STRING)
	private RoleNames role;

	public Role(RoleNames role) {
		this.role = role;
	}
}
