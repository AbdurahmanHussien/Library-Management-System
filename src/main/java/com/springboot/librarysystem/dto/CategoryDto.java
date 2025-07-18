package com.springboot.librarysystem.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class CategoryDto {

	private Long id;
	@NotBlank(message = "Name is required")
	@Size(min = 3, max = 10, message = "Name must be between 3 and 10 characters")
	private String name;

	private Long parentId;

	private List<BookDto> books;

}
