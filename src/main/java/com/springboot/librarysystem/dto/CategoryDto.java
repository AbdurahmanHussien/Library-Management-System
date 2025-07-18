package com.springboot.librarysystem.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryDto {

	private Long id;
	@NotBlank(message = "name.is.required")
	@Size(min = 3, max = 10, message = "name.short.or.long")
	private String name;

	private Long parentId;

}
