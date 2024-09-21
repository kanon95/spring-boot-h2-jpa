package com.practice.springh2jpa.component.member.domain.store;

import java.util.List;
import java.util.Optional;

import com.practice.springh2jpa.component.member.domain.entity.Member;
import org.springframework.data.domain.PageRequest;


public interface MemberStore {
  List<Member> findAll();

  List<Member> findAllByName(String name, PageRequest pageRequest);

  Optional<Member> findByMemberKey(Long memberKey);

  Optional<Member> findByMemberId(String memberId);

  Member save(Member member);

  void deleteByMemberKey(Long memberKey);

  void deleteByMemberId(String memberId);


}
