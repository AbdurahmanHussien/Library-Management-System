package com.springboot.librarysystem.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class BookDto {
	private Long id;

	@NotBlank(message = "title.is.required")
	@Size(min = 2, max = 20, message = "title.short.or.long")
	private String title;

	@NotBlank(message = "isbn.is.required")
	@Size(min = 10, max = 13, message = "isbn.short.or.long")
	private String isbn;

	@NotBlank(message = "edition.is.required")
	private String edition;

	@Min(value = 1500, message = "publication.year.min")
	@Max(value = 2025, message = "publication.year.max")
	private int publicationYear;

	@Size(max = 300, message = "summary.max")
	private String summary;

	@Size(max = 100, message = "cover.image.url.max")
	private String coverImageUrl;

	@NotNull(message = "language.is.required")
	private Long languageId;

	@NotNull(message = "publisher.is.required")
	private Long publisherId;

	@NotEmpty(message = "authors.is.required")
	private List<Long> authorIds;

	@NotEmpty(message = "categories.is.required")
	private List<Long> categoryIds;
}
