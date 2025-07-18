package com.springboot.librarysystem.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
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

	@Schema(examples = "1", description = "Category ID")
	private Long id;

	@NotBlank(message = "name.is.required")
	@Size(min = 3, max = 10, message = "name.short.or.long")
	@Schema(examples = "Fiction", description = "Category name")
	private String name;

	@Schema(examples = "1", description = "Parent category ID")
	private Long parentId;

}
