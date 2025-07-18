package com.springboot.librarysystem.entity;

import jakarta.persistence.Entity;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Publisher extends BaseEntity {
	private String address;
	private String contactEmail;
}
