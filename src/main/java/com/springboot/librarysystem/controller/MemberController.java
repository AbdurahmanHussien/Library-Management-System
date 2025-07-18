package com.springboot.librarysystem.controller;


import com.springboot.librarysystem.config.UserActivityLogger;
import com.springboot.librarysystem.dto.MemberDto;
import com.springboot.librarysystem.dto.PublisherDto;
import com.springboot.librarysystem.service.IMemberService;
import com.springboot.librarysystem.service.IPublisherService;
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
	private final UserActivityLogger userActivityLogger;



	@GetMapping
	@Operation(summary = "Get all members")
	public ResponseEntity<List<MemberDto>> getAllMembers() {
		List<MemberDto> members = memberService.getAllMembers();
		userActivityLogger.logUserAction("Get","All members");
		return ResponseEntity.ok().body(members);

	}

	@GetMapping("/{id}")
	@Operation(summary = "Get member by id")
	public ResponseEntity<MemberDto> getMemberById(@PathVariable Long id) {
		MemberDto member = memberService.getMemberById(id);
		userActivityLogger.logUserAction("Get","member by id: " + id);
		return ResponseEntity.ok().body(member);
	}

	@PostMapping
	@Operation(summary = "Create member")
	public ResponseEntity<MemberDto> createMember(@Valid @RequestBody MemberDto memberDto) {
		MemberDto member = memberService.createMember(memberDto);
		userActivityLogger.logUserAction("Post", "Create member");
		return ResponseEntity.ok().body(member);
	}

	@PutMapping
	@Operation(summary = "Update member")
	public ResponseEntity<MemberDto> updateMember(@Valid @RequestBody MemberDto memberDto) {
		MemberDto member = memberService.updateMember(memberDto);
		userActivityLogger.logUserAction("Put", "Update member with id: " + memberDto.getId());
		return ResponseEntity.ok().body(member);
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Delete member")
	public ResponseEntity<?> deleteMember(@PathVariable Long id) {
		memberService.deleteMemberById(id);
		userActivityLogger.logUserAction("Delete", "Delete member with id: " + id);
		return ResponseEntity.ok("member deleted successfully");
	}

}
