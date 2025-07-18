package com.springboot.librarysystem.service.impl;

import com.springboot.librarysystem.dto.MemberDto;
import com.springboot.librarysystem.entity.Member;
import com.springboot.librarysystem.exception.BadRequestException;
import com.springboot.librarysystem.exception.ResourceNotFoundException;
import com.springboot.librarysystem.mapper.MemberMapper;
import com.springboot.librarysystem.repository.MemberRepository;
import com.springboot.librarysystem.service.IMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements IMemberService {

	private final MemberRepository memberRepository;

	@Override
	public MemberDto createMember(MemberDto dto) {
		if (Objects.nonNull(dto.getId())) {
			throw new BadRequestException("Id must be null");
		}

		Member member = MemberMapper.INSTANCE.toEntity(dto);
		return MemberMapper.INSTANCE.toDto(memberRepository.save(member));
	}

	@Override
	public List<MemberDto> getAllMembers() {
		List<Member> members = memberRepository.findAll();
		return MemberMapper.INSTANCE.toDtoList(members);
	}

	@Override
	public MemberDto getMemberById(Long id) {
		Member member = memberRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Member not found"));
		return MemberMapper.INSTANCE.toDto(member);
	}

	@Override
	public void deleteMemberById(Long id) {
		Member member = memberRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Member not found"));
		memberRepository.delete(member);
	}

	@Override
	public MemberDto updateMember(MemberDto dto) {
		if (Objects.isNull(dto.getId())) {
			throw new BadRequestException("Id cannot be null");
		}

		getMemberById(dto.getId());

		Member member = MemberMapper.INSTANCE.toEntity(dto);
		return MemberMapper.INSTANCE.toDto(memberRepository.save(member));
	}
}
