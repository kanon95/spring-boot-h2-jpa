package com.practice.springh2jpa.component.member.store.repository;

import java.time.LocalDateTime;

public interface MemberInterface {
  Long getMemberKey();
  String getMemberId();
  String getName();
  String getEmail();
  int getAge();
  LocalDateTime getBirthday();
}
