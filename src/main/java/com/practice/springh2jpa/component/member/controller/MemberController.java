package com.practice.springh2jpa.component.member.controller;

import com.practice.springh2jpa.component.member.adapter.dto.MemberDto;
import com.practice.springh2jpa.component.member.domain.entity.Member;
import com.practice.springh2jpa.component.member.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
  private final MemberService memberService;

  @GetMapping("{memberId}")
  public ResponseEntity<MemberDto> find(@PathVariable String memberId ) {
    Member member = memberService.findByMemberId(memberId);
    MemberDto dto = MemberDto.from(member);
    return ResponseEntity.ok(dto);
  }

  @PostMapping
  public ResponseEntity<MemberDto> register(@Valid @RequestBody MemberDto memberDto) {
    Member member = memberService.register(memberDto);
    return ResponseEntity.ok(MemberDto.from(member));
  }

  @PutMapping
  public ResponseEntity<MemberDto> modify(@Valid @RequestBody MemberDto memberDto) {
    Member member = memberService.modify(memberDto);
    return ResponseEntity.ok(MemberDto.from(member));
  }

  @DeleteMapping("{memberId}")
  public void remove(@PathVariable String memberId) {
    memberService.remove(memberId);
  }

}
