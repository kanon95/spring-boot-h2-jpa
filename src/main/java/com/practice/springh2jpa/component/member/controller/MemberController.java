package com.practice.springh2jpa.component.member.controller;

import com.practice.springh2jpa.component.member.adapter.dto.MemberDto;
import com.practice.springh2jpa.component.member.domain.entity.Member;
import com.practice.springh2jpa.component.member.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
@Tag(name = "사용자", description = "사용자 CRUD 서비스 제공 <br/> API 설명")
public class MemberController {

  private final MemberService memberService;

  @GetMapping("/list/{name}")
  public ResponseEntity<List<MemberDto>> findAllByName(
      @PathVariable String name
  ) {
    List<Member> list = memberService.findAllByName(name, 0, 10);
    return ResponseEntity.ok(list.stream().map(MemberDto::from).toList());
  }

  @Operation(
      summary = "사용자 조회",
      description = "사용자ID(memberId)로 사용자를 조회 합니다.",
      responses = {
          @ApiResponse( responseCode = "200", description = "사용자 조회 성공"),
          @ApiResponse( responseCode = "404", description = "사용자 조회 실패")
      }
  )
  @Parameters ({
      @Parameter(name = "dummy1", description = "더미 파라미터1 - queryString 예시1", example = "dummyValue1"),
      @Parameter(name = "dummy2", description = "더미 파라미터2 - queryString 예시2", example = "dummyValue2")
  })
  @GetMapping("{memberId}")
  public ResponseEntity<MemberDto> find(
      @Parameter (description = "사용자ID(memberId)" , example = "test1")
      @PathVariable String memberId ) {
    Member member = memberService.findByMemberId(memberId);
    MemberDto dto = MemberDto.from(member);
    return ResponseEntity.ok(dto);
  }

  @Operation(
      summary = "사용자 등록",
      description = "사용자를 등록합니다.",
      requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
          content = @Content(
              mediaType = MediaType.APPLICATION_JSON_VALUE,
              schema = @Schema( implementation = MemberDto.class ),
              examples = {
                  @ExampleObject(name = "memberDto 회원등록 예제1", value = """
                      {
                        "memberId" : "test1",
                        "name" : "테스터1",
                        "email" : "test1@site.com",
                        "age" : "31",
                        "birthday" : "2024-09-21"
                      }
                      """,
                  description = "신규로 회원을 등록합니다.#1"),
                  @ExampleObject(name = "memberDto 회원등록 예제2", value = """
                      {
                        "memberId" : "test2",
                        "name" : "테스터2",
                        "email" : "test2@site.com",
                        "age" : "32",
                        "birthday" : "2024-09-22"
                      }
                      """,
                      description = "신규로 회원을 등록합니다.#2"),

              }
          )
      ),
      responses = {
          @ApiResponse( responseCode = "200", description = "사용자 등록 성공")
      }
  )
  @PostMapping
  public ResponseEntity<MemberDto> register(@Valid @RequestBody MemberDto memberDto) {
    Member member = memberService.register(memberDto);
    return ResponseEntity.ok(MemberDto.from(member));
  }

  @PutMapping
  public ResponseEntity<MemberDto> modify(@Valid @RequestBody MemberDto memberDto) {
    Member member = memberService.modify(memberDto);
    return ResponseEntity.ok(MemberDto.from(member));
  }

  @DeleteMapping("{memberId}")
  public void remove(@PathVariable String memberId) {
    memberService.remove(memberId);
  }

}
