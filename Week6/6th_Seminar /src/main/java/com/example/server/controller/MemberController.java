package com.example.server.controller;

import com.example.server.dto.member.MemberCreateRequest;
import com.example.server.dto.member.MemberGetResponse;
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

    @GetMapping("{memberId}")
    public ResponseEntity<MemberGetResponse> getMemberProfileV1(@PathVariable("memberId") Long memberId) {
        return ResponseEntity.ok(memberService.getByIdV1(memberId));
    }

    @GetMapping(value = "{memberId}/v2", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MemberGetResponse> getMemberProfileV2(@PathVariable Long memberId) {
        return ResponseEntity.ok(memberService.getByIdV2(memberId));
    }

    @GetMapping
    public ResponseEntity<List<MemberGetResponse>> getMembersProfile() {
        return ResponseEntity.ok(memberService.getMembers());
    }

    @PostMapping
    public ResponseEntity<Void> createMember(@RequestBody MemberCreateRequest requset) {
        URI location = URI.create("api/member/" + memberService.create(requset));
        return ResponseEntity.created(location).build();
    }
}
