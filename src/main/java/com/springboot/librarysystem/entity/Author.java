package com.springboot.librarysystem.entity;

import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter
@SuperBuilder
public class Author extends BaseEntity {

	private String bio;
}

