package com.springboot.librarysystem.mapper;

import com.springboot.librarysystem.dto.MemberDto;
import com.springboot.librarysystem.entity.Member;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import java.util.List;

@Mapper(componentModel = "spring")
public interface MemberMapper {

	MemberMapper INSTANCE = Mappers.getMapper(MemberMapper.class);

	MemberDto toDto(Member member);

	Member toEntity(MemberDto dto);

	List<MemberDto> toDtoList(List<Member> members);

}
