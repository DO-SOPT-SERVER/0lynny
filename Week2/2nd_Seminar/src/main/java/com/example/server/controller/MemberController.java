package com.example.server.controller;

import com.example.server.domain.Member;
import com.example.server.common.GenericResponse;
import com.example.server.dto.request.MemberCreateRequest;
import com.example.server.dto.response.MemberGetResponse;
import com.example.server.common.StatusCode;
import com.example.server.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/member")
public class MemberController {
    private  final MemberService memberService;

    @GetMapping("/{memberId}")
    public GenericResponse<MemberGetResponse> getMemberProfileV1(@PathVariable("memberId") Long memberId) {
        return new GenericResponse<MemberGetResponse>(StatusCode.OK.getStatusCode(), "사용자를 조회했습니다.", true,  memberService.getByIdV1(memberId));
    }

    @GetMapping(value = "/{memberId}/v2", produces = MediaType.APPLICATION_JSON_VALUE)
    public  GenericResponse<MemberGetResponse> getMemberProfileV2(@PathVariable Long memberId) {
        return new GenericResponse<MemberGetResponse>(StatusCode.OK.getStatusCode(), "사용자를 조회했습니다.", true, memberService.getByIdV2(memberId));
    }

    @GetMapping
    public GenericResponse<List<MemberGetResponse>> getMembersProfile() {
        return new GenericResponse<List<MemberGetResponse>>(StatusCode.OK.getStatusCode(), "사용자 전체 목록을 조회했습니다.", true, memberService.getMembers());
    }

    @PostMapping
    public GenericResponse<Member> createMember(@RequestBody MemberCreateRequest request) {
        Member newMember = memberService.createMember(request);
        return new GenericResponse<Member>(StatusCode.CREATED.getStatusCode(), "사용자를 생성하였습니다.", true, newMember);
    }
    @DeleteMapping("/{memberId}")
    public GenericResponse<MemberGetResponse> deleteMember(@PathVariable("memberId") Long memberId) {
        memberService.deleteMember(memberId);
        return new GenericResponse<MemberGetResponse>(StatusCode.NO_CONTENT.getStatusCode(), "사용자를 삭제하였습니다.", true);
    }

    @PatchMapping("/{memberId}")
    public GenericResponse<Member> updateMember(@RequestBody MemberCreateRequest request, @PathVariable("memberId") Long memberId) {
        return new GenericResponse<Member>(StatusCode.OK.getStatusCode(), "사용자 정보를 수정하였습니다.", true, memberService.updateMember(request, memberId));
    }
}
