package com.practice.springh2jpa.component.member.service;

import com.practice.springh2jpa.component.member.adapter.dto.MemberDto;
import com.practice.springh2jpa.component.member.domain.entity.Member;
import com.practice.springh2jpa.component.member.domain.store.MemberStore;
import com.practice.springh2jpa.component.member.exception.MemberNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;


@Service
@RequiredArgsConstructor
public class MemberService {
  private final MemberStore memberStore;
  private final ModelMapper modelMapper;

  public List<Member> findAllByName(String name, int page, int size) {
    PageRequest pageRequest = PageRequest.of(page, size);
    return memberStore.findAllByName(name,pageRequest);
  }

  public Member findByMemberId(String memberId) {
    return memberStore.findByMemberId(memberId)
        .orElseThrow(()->new MemberNotFoundException("Member with id " + memberId + " not found"));
  }

  public Member findByMemberIdOrDefault(String memberId) {
    return  memberStore.findByMemberId(memberId).orElseGet(()-> Member.defaultValue(memberId));
  }


  public Member register(MemberDto memberDto) {
    return memberStore.save(memberDto.toDomain());
  }

  public Member modify(MemberDto memberDto) {
    Member member = this.findByMemberId(memberDto.getMemberId());
    member.setValues(memberDto.getName(), memberDto.getEmail(), memberDto.getAge(),
        memberDto.getBirthday());
    return memberStore.save(member);
  }

  public void remove(String memberId) {
    Member member = this.findByMemberId(memberId);
    memberStore.deleteByMemberKey(member.getMemberKey());
  }

  public Boolean exist(String memberId) {
    return memberStore.findByMemberId(memberId).isPresent();
  }

}
