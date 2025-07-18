package com.springboot.librarysystem.entity;

import jakarta.persistence.Entity;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Member extends BaseEntity {

	private String email;
	private String phone;
	private String address;
	private LocalDateTime createdAt = LocalDateTime.now();
}
