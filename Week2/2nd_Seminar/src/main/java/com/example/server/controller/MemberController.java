package com.example.server.controller;

import com.example.server.domain.Member;
import com.example.server.dto.GenericResponse;
import com.example.server.dto.MemberCreateRequest;
import com.example.server.dto.MemberGetResponse;
import com.example.server.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/member")
public class MemberController {
    private  final MemberService memberService;

    @GetMapping("/{memberId}")
    public GenericResponse<MemberGetResponse> getMemberProfileV1(@PathVariable("memberId") Long memberId) {
        return new GenericResponse<MemberGetResponse>(200, "사용자를 조회했습니다.", true,  memberService.getByIdV1(memberId));
    }

    @GetMapping(value = "/{memberId}/v2", produces = MediaType.APPLICATION_JSON_VALUE)
    public  GenericResponse<MemberGetResponse> getMemberProfileV2(@PathVariable Long memberId) {
        return new GenericResponse<MemberGetResponse>(200, "사용자를 조회했습니다.", true, memberService.getByIdV2(memberId));
    }

    @GetMapping
    public GenericResponse<List<MemberGetResponse>> getMembersProfile() {
        return new GenericResponse<List<MemberGetResponse>>(200, "사용자 전체 목록을 조회했습니다.", true, memberService.getMembers());
    }

    @PostMapping
    public GenericResponse<Member> createMember(@RequestBody MemberCreateRequest request) {
        Member newMember = memberService.create(request);
        return new GenericResponse<Member>(200, "사용자를 생성하였습니다.", true, newMember);
    }
}
