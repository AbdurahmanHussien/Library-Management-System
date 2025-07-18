package com.springboot.librarysystem.service.impl;

import com.springboot.librarysystem.dto.PublisherDto;
import com.springboot.librarysystem.entity.Publisher;
import com.springboot.librarysystem.exception.BadRequestException;
import com.springboot.librarysystem.exception.ResourceNotFoundException;
import com.springboot.librarysystem.mapper.PublisherMapper;
import com.springboot.librarysystem.repository.PublisherRepository;
import com.springboot.librarysystem.service.IPublisherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PublisherServiceImpl implements IPublisherService {

	private final PublisherRepository publisherRepository;

	@Override
	public PublisherDto addPublisher(PublisherDto dto) {
		if (Objects.nonNull(dto.getId())) {
			throw new BadRequestException("Id must be null");
		}
		Publisher publisher = toEntity(dto);
		return toDto(publisherRepository.save(publisher));
	}

	@Override
	public List<PublisherDto> getAllPublishers() {
		return publisherRepository.findAll()
				.stream()
				.map(this::toDto)
				.collect(Collectors.toList());
	}

	@Override
	public PublisherDto getPublisherById(Long id) {
		Publisher publisher = publisherRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Publisher not found"));
		return toDto(publisher);
	}

	@Override
	public PublisherDto updatePublisher(PublisherDto dto) {
		if (Objects.isNull(dto.getId())) {
			throw new BadRequestException("Id cannot be null");
		}

		getPublisherById(dto.getId());

		Publisher publisher = toEntity(dto);
		return toDto(publisherRepository.save(publisher));
	}

	@Override
	public void deletePublisher(Long id) {
		Publisher publisher = publisherRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Publisher not found"));
		publisherRepository.delete(publisher);
	}

	private Publisher toEntity(PublisherDto dto) {
		return PublisherMapper.INSTANCE.toEntity(dto);
	}

	private PublisherDto toDto(Publisher publisher) {
		return PublisherMapper.INSTANCE.toDto(publisher);
	}
}
