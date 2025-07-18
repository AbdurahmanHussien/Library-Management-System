package com.springboot.librarysystem.service;

import com.springboot.librarysystem.dto.MemberDto;
import java.util.List;

public interface IMemberService {

	MemberDto createMember(MemberDto dto);

	List<MemberDto> getAllMembers();

	MemberDto getMemberById(Long id);

	void deleteMemberById(Long id);

	MemberDto updateMember(MemberDto dto);

}
