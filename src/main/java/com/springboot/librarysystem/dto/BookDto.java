package com.springboot.librarysystem.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class BookDto implements Serializable {
	private Long id;

	@NotBlank(message = "title.is.required")
	@Size(min = 2, max = 20, message = "title.short.or.long")
	@Schema(example = "The Great Gatsby", description = "Book title")
	private String title;

	@NotBlank(message = "isbn.is.required")
	@Size(min = 10, max = 13, message = "isbn.short.or.long")
	@Schema(example = "978-0-06-112546-2", description = "ISBN-13")
	private String isbn;

	@NotBlank(message = "edition.is.required")
	@Schema(example = "1st", description = "Book edition")
	private String edition;

	@Min(value = 1500, message = "publication.year.min")
	@Max(value = 2025, message = "publication.year.max")
	@Schema(example = "1925", description = "Publication year")
	private int publicationYear;

	@Size(max = 300, message = "summary.max")
	@Schema(example = "A novel by F. Scott Fitzgerald", description = "Book summary")
	private String summary;

	@Size(max = 100, message = "cover.image.url.max")
	@Schema(example = "https://example.com/cover.jpg", description = "Book cover image URL")
	private String coverImageUrl;

	@NotNull(message = "language.is.required")
	@Schema(example = "1", description = "Language ID")
	private Long languageId;

	@NotNull(message = "publisher.is.required")
	@Schema(example = "1", description = "Publisher ID")
	private Long publisherId;

	@NotEmpty(message = "authors.is.required")
	@Schema(example = "[1, 2]", description = "Author IDs")
	private List<Long> authorIds;

	@NotEmpty(message = "categories.is.required")
	@Schema(example = "[1, 2]", description = "Category IDs")
	private List<Long> categoryIds;
}
