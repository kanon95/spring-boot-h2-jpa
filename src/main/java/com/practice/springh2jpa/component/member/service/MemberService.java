package com.practice.springh2jpa.component.member.service;

import com.practice.springh2jpa.component.member.adapter.dto.MemberDto;
import com.practice.springh2jpa.component.member.domain.entity.Member;
import com.practice.springh2jpa.component.member.domain.store.MemberStore;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;


@Service
@RequiredArgsConstructor
public class MemberService {
  private final MemberStore memberStore;
  private final ModelMapper modelMapper;

  public Member findByMemberId(String memberId) {
    return memberStore.findByMemberId(memberId)
        .orElseThrow(()->new NoSuchElementException("Member with id " + memberId + " not found"));
  }

  public MemberDto getMemberDto(Member member) {
    return modelMapper.map(member, MemberDto.class);
  }

  public Member findByMemberIdOrDefault(String memberId) {
    return  memberStore.findByMemberId(memberId).orElseGet(()-> Member.defaultValue(memberId));
  }

  public String register(MemberDto memberDto) {
    Member member = memberDto.toDomain();
    memberStore.save(member);
    return member.getMemberId();
  }

  public void modify(MemberDto memberDto) {

    Member member = this.findByMemberId(memberDto.getMemberId());
    member.setValues(memberDto.getName(), memberDto.getEmail(), memberDto.getAge(),
        memberDto.getBirthday());
    memberStore.save(member);
  }

  public void remove(String memberId) {
    Member member = this.findByMemberId(memberId);
    memberStore.deleteByMemberKey(member.getMemberKey());
  }

  public Boolean exist(String memberId) {
    return memberStore.findByMemberId(memberId).isPresent();
  }

}
