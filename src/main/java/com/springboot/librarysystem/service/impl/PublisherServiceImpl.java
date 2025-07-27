package com.springboot.librarysystem.service.impl;

import com.springboot.librarysystem.dto.PublisherDto;
import com.springboot.librarysystem.entity.Publisher;
import com.springboot.librarysystem.exception.BadRequestException;
import com.springboot.librarysystem.exception.ResourceNotFoundException;
import com.springboot.librarysystem.mapper.PublisherMapper;
import com.springboot.librarysystem.repository.PublisherRepository;
import com.springboot.librarysystem.service.IPublisherService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PublisherServiceImpl implements IPublisherService {

	private final PublisherRepository publisherRepository;

	@Override
	@CacheEvict(value = "publishers", allEntries = true)
	public PublisherDto addPublisher(PublisherDto dto) {
		if (Objects.nonNull(dto.getId())) {
			throw new BadRequestException("id.must.be.null");
		}
		Publisher publisher = toEntity(dto);
		return toDto(publisherRepository.save(publisher));
	}

	@Override
	@Cacheable(value = "publishers")
	public List<PublisherDto> getAllPublishers() {
		List<PublisherDto> publishers = publisherRepository.findAll()
				.stream()
				.map(this::toDto)
				.toList();

		if (publishers.isEmpty()) {
			throw new ResourceNotFoundException("no.publishers.found");
		}
		return publishers;
	}

	@Override
	public PublisherDto getPublisherById(Long id) {
		Publisher publisher = publisherRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("publisher.not.found"));
		return toDto(publisher);
	}

	@Override
	@CacheEvict(value = "publishers", allEntries = true)
	public PublisherDto updatePublisher(PublisherDto dto) {
		if (Objects.isNull(dto.getId())) {
			throw new BadRequestException("id.required");
		}

		getPublisherById(dto.getId());

		Publisher publisher = toEntity(dto);
		return toDto(publisherRepository.save(publisher));
	}

	@Override
	@CacheEvict(value = "publishers", allEntries = true)
	public void deletePublisher(Long id) {
		Publisher publisher = publisherRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("publisher.not.found"));
		publisherRepository.delete(publisher);
	}

	private Publisher toEntity(PublisherDto dto) {
		return PublisherMapper.INSTANCE.toEntity(dto);
	}

	private PublisherDto toDto(Publisher publisher) {
		return PublisherMapper.INSTANCE.toDto(publisher);
	}
}
