package com.springboot.librarysystem.entity.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Table(name = "Library_users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String username;

	private String email;

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;

	@ManyToMany(fetch = FetchType.EAGER , cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(
			name = "library_user_roles",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "role_id")
	)
	private Set<Role> roles = new HashSet<>();

	private boolean isActive = true;

	@Column(updatable = false)
	private LocalDateTime createdAt;


	@PrePersist
	public void prePersist() {
		createdAt = LocalDateTime.now();
	}


}
