package com.practice.springh2jpa.component.member.store.jpo;

import com.practice.springh2jpa.component.member.domain.entity.Member;
import com.practice.springh2jpa.component.member.store.repository.MemberRepository;
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
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Comment;
import org.modelmapper.ModelMapper;

@Getter
@Entity
@Table(name = "T_MEMBER")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Comment("회원")
public class MemberJpo {

  @Id
  @Comment("회원 Key - 관리번호")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long memberKey;

  @Comment("회원 ID")
  @Column(unique = true, nullable = false)
  private String memberId;

  @Comment("회원 이름")
  @Column(nullable = false, length = 50)
  private String name;

  @Comment("회원 Email")
  @Column(unique = true, nullable = false)
  private String email;

  @Comment("회원 나이")
  @Column(nullable = true)
  private int age;

  @Comment("회원 생년월일")
  @Column(nullable = true)
  private LocalDate birthday;

  public MemberJpo(Member member) {
    this.setValues(member);
  }

  public void setValues(Member member) {
    this.memberKey = member.getMemberKey();
    this.memberId = member.getMemberId();
    this.name = member.getName();
    this.email = member.getEmail();
    this.age = member.getAge();
    this.birthday = member.getBirthday();
  }

  public Member toDomain() {
    return new Member(memberKey, memberId, name, email, age, birthday);
  }
}
