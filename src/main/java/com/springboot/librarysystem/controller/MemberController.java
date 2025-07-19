package com.springboot.librarysystem.controller;


import com.springboot.librarysystem.service.UserLogService;
import com.springboot.librarysystem.dto.MemberDto;
import com.springboot.librarysystem.service.IMemberService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

	private final IMemberService memberService;
	private final UserLogService userLogService;



	@GetMapping
	@Operation(summary = "Get all members")
	public ResponseEntity<List<MemberDto>> getAllMembers() {
		List<MemberDto> members = memberService.getAllMembers();
		return ResponseEntity.ok().body(members);

	}

	@GetMapping("/{id}")
	@Operation(summary = "Get member by id")
	public ResponseEntity<MemberDto> getMemberById(@PathVariable Long id) {
		MemberDto member = memberService.getMemberById(id);
		return ResponseEntity.ok().body(member);
	}

	@PostMapping
	@Operation(summary = "Create member")
	public ResponseEntity<MemberDto> createMember(@Valid @RequestBody MemberDto memberDto) {
		MemberDto member = memberService.createMember(memberDto);
		userLogService.logUserAction("Post", "Create member with name : " + memberDto.getName());
		return ResponseEntity.ok().body(member);
	}

	@PutMapping
	@Operation(summary = "Update member")
	public ResponseEntity<MemberDto> updateMember(@Valid @RequestBody MemberDto memberDto) {
		MemberDto member = memberService.updateMember(memberDto);
		userLogService.logUserAction("Put", "Update member with id: " + memberDto.getId());
		return ResponseEntity.ok().body(member);
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Delete member")
	public ResponseEntity<?> deleteMember(@PathVariable Long id) {
		memberService.deleteMemberById(id);
		userLogService.logUserAction("Delete", "Delete member with id: " + id);
		return ResponseEntity.ok("member deleted successfully");
	}

}
