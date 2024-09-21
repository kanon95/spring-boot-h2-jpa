package com.practice.springh2jpa.component.member.adapter.dto;

import com.practice.springh2jpa.component.member.domain.entity.Member;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Schema(name = "MemberDto", description = "사용자 정보 Parameter 객체 - 사용자 Parameter를 기술합니다.")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class MemberDto implements Serializable {
  @Schema (description = "사용자 회원번호. 등록 시, 부여됨. 외부에서 설정하지 않음.")
  private Long memberKey;

  @Schema (description = "사용자 회원ID", example = "test1")
  @NotBlank
  private String memberId;

  @Schema (description = "사용자 이름", example = "테스터1")
  @NotBlank
  @Size(min=1, max=50)
  private String name;

  @Schema (description = "사용자 email", example = "test1@site.com")
  @NotBlank
  private String email;

  @Schema (description = "사용자 나이", example = "31")
  private int age;

  @Schema (description = "생년월일", example = "2024-09-11")
  private LocalDate birthday;

  public void setValue(Long memberKey, String memberId, String name, String email, int age, LocalDate birthday) {
    this.memberKey = memberKey;
    this.memberId = memberId;
    this.name = name;
    this.email = email;
    this.age = age;
    this.birthday = birthday;
  }

  public static MemberDto from(Member member) {
   return new MemberDto( member.getMemberKey(),
        member.getMemberId(),
        member.getName(),
        member.getEmail(),
        member.getAge(),
        member.getBirthday()
    );
  }

  public Member toDomain() {
    return new Member(this.memberKey, this.memberId, this.name, this.email, this.age, this.birthday);
  }
}
