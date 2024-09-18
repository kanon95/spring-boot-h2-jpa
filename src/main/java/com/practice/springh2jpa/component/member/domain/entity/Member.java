package com.practice.springh2jpa.component.member.domain.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Getter
@Entity
@Table
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

  @Id
  @Comment("회원 관리 번호")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long number;

  @Comment("회원 ID")
  @Column(unique = true, nullable = false)
  private String id;

  @Comment("회원 이름")
  @Column(nullable = false, length = 50)
  private String name;

  @Comment("회원 Email")
  @Column(unique = true, nullable = false)
  private String email;

  @Comment("회원 나이")
  @Column(nullable = false)
  private int age;

  @Comment("회원 생년월일")
  @Column(nullable = false)
  private LocalDate birthday;

  public Member(Long number, String id, String name, String email, int age, LocalDate birthday) {
    this.number = number;
    this.id = id;
    this.name = name;
    this.email = email;
    this.age = age;
    this.birthday = birthday;
  }

}
