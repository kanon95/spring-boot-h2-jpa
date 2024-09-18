package com.practice.springh2jpa.component.member.store;

import com.practice.springh2jpa.component.member.domain.entity.Member;
import com.practice.springh2jpa.component.member.domain.store.MemberStore;
import com.practice.springh2jpa.component.member.store.jpo.MemberJpo;
import com.practice.springh2jpa.component.member.store.repository.MemberRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class MemberJpaStore implements MemberStore {

  private MemberRepository memberRepository;

  @Override
  public List<Member> findAll() {
    return StreamSupport.stream(memberRepository.findAll().spliterator(), false)
        .map(MemberJpo::toDomain)
        .collect(Collectors.toList());
  }

  @Override
  public Optional<Member> findByMemberKey(Long memberKey) {
    return memberRepository.findById(memberKey).map(MemberJpo::toDomain);
  }

  @Override
  public Optional<Member> findByMemberId(String memberId) {
    List<MemberJpo> memberJpoList =  StreamSupport.stream(memberRepository.findAll().spliterator(), false)
        .filter(memberJpo -> memberJpo.getMemberId().equals(memberId))
        .toList();
    return Optional.of(memberJpoList.get(0).toDomain());
  }

  @Override
  public void save(Member member) {
    memberRepository.save(new MemberJpo(member));
  }

  @Override
  public void deleteByMemberKey(Long memberKey) {
    memberRepository.deleteById(memberKey);
  }

  @Override
  public void deleteByMemberId(String memberId) {
    this.findByMemberId(memberId)
        .ifPresent(member -> {
          memberRepository.deleteById(member.getMemberKey());
        });
  }
}
