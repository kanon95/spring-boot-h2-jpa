package com.practice.springh2jpa.component.member.domain.store;

import java.util.List;
import java.util.Optional;

import com.practice.springh2jpa.component.member.domain.entity.Member;


public interface MemberStore {
  List<Member> findAll();

  Optional<Member> findByMemberKey(Long memberKey);

  Optional<Member> findByMemberId(String memberId);

  Member save(Member member);

  void deleteByMemberKey(Long memberKey);

  void deleteByMemberId(String memberId);


}
