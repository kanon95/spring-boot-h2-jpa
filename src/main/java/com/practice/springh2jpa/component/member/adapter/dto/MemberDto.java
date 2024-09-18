package com.practice.springh2jpa.component.member.adapter.dto;

import com.practice.springh2jpa.component.member.domain.entity.Member;
import java.io.Serializable;
import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class MemberDto implements Serializable {
  private Long memberKey;

  @NotBlank
  private String memberId;

  @NotBlank
  @Size(min=1, max=50)
  private String name;

  @NotBlank
  private String email;

  private int age;

  private LocalDate birthday;

  public void setValue(Long memberKey, String memberId, String name, String email, int age, LocalDate birthday) {
    this.memberKey = memberKey;
    this.memberId = memberId;
    this.name = name;
    this.email = email;
    this.age = age;
    this.birthday = birthday;
  }

  public Member toDomain() {
    return new Member(this.memberKey, this.memberId, this.name, this.email, this.age, this.birthday);
  }
}
