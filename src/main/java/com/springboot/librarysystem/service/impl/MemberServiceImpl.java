package com.springboot.librarysystem.service.impl;

import com.springboot.librarysystem.dto.MemberDto;
import com.springboot.librarysystem.entity.Member;
import com.springboot.librarysystem.exception.BadRequestException;
import com.springboot.librarysystem.exception.ResourceNotFoundException;
import com.springboot.librarysystem.mapper.MemberMapper;
import com.springboot.librarysystem.repository.MemberRepository;
import com.springboot.librarysystem.service.IMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements IMemberService {

	private final MemberRepository memberRepository;

	@Override
	@CacheEvict(value = "members", allEntries = true)
	public MemberDto createMember(MemberDto dto) {
		if (Objects.nonNull(dto.getId())) {
			throw new BadRequestException("id.must.be.null");
		}

		Member member = MemberMapper.INSTANCE.toEntity(dto);
		member.setCreatedAt(LocalDateTime.now());
		return MemberMapper.INSTANCE.toDto(memberRepository.save(member));
	}

	@Override
	@Cacheable(value = "members")
	public List<MemberDto> getAllMembers() {
		List<Member> members = memberRepository.findAll();
		if (members.isEmpty()) {
			throw new ResourceNotFoundException("no.members.found");
		}
		return MemberMapper.INSTANCE.toDtoList(members);
	}

	@Override
	public MemberDto getMemberById(Long id) {
		Member member = memberRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("member.not.found"));
		return MemberMapper.INSTANCE.toDto(member);
	}

	@Override
	@CacheEvict(value = "members", allEntries = true)
	public void deleteMemberById(Long id) {
		Member member = memberRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("member.not.found"));
		memberRepository.delete(member);
	}

	@Override
	@CacheEvict(value = "members", allEntries = true)
	public MemberDto updateMember(MemberDto dto) {
		if (Objects.isNull(dto.getId())) {
			throw new BadRequestException("id.required");
		}

		getMemberById(dto.getId());

		Member member = MemberMapper.INSTANCE.toEntity(dto);
		return MemberMapper.INSTANCE.toDto(memberRepository.save(member));
	}
}
