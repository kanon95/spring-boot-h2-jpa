package com.practice.springh2jpa.component.member.domain.entity;

import java.time.LocalDate;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

  private Long memberKey;

  private String memberId;

  private String name;

  private String email;

  private int age;

  private LocalDate birthday;

  public Member(Long memberKey, String memberId, String name, String email, int age, LocalDate birthday) {
    this.memberKey = memberKey;
    this.memberId = memberId;
    this.name = name;
    this.email = email;
    this.age = age;
    this.birthday = birthday;
  }

  public void setValues(String name, String email, int age, LocalDate birthday) {
    this.name = name;
    this.email = email;
    this.age = age;
    this.birthday = birthday;
  }

  public static Member defaultValue(String id) {
    return new Member(null,id,"새 회원","new@site.com",50, LocalDate.now());
  }
}
