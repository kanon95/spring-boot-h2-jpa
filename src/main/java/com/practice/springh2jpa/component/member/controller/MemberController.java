package com.practice.springh2jpa.component.member.controller;

import com.practice.springh2jpa.component.member.adapter.dto.MemberDto;
import com.practice.springh2jpa.component.member.domain.entity.Member;
import com.practice.springh2jpa.component.member.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
  public MemberDto find(@PathVariable String memberId ) {
    Member member = memberService.findByMemberId(memberId);
    return memberService.getMemberDto(member);
  }

  @PostMapping
  public String register(@Valid @RequestBody MemberDto memberDto) {
    return memberService.register(memberDto);
  }

  @PutMapping
  public void modify(@Valid @RequestBody MemberDto memberDto) {
    memberService.modify(memberDto);
  }

  @DeleteMapping("{memberId}")
  public void remove(@PathVariable String memberId) {
    memberService.remove(memberId);
  }

}
