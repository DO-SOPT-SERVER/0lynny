package com.example.server.service;

import com.example.server.domain.Member;
import com.example.server.dto.request.MemberCreateRequest;
import com.example.server.dto.response.MemberGetResponse;
import com.example.server.repository.MemberJpaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberJpaRepository memberJpaRepository;

    public MemberGetResponse getByIdV1(Long memberId) {
        Member member = memberJpaRepository.findById(memberId).get();
        MemberGetResponse response = MemberGetResponse.of(member);
        return response;
    }

    public MemberGetResponse getByIdV2(Long memberId) {
        return MemberGetResponse.of(memberJpaRepository.findByIdOrThrow(memberId));
    }

    public List<MemberGetResponse> getMembers() {
        return memberJpaRepository.findAll()
                .stream()
                .map(MemberGetResponse::of)
                .collect(Collectors.toList());
    }

    @Transactional
    public Member createMember(MemberCreateRequest request) {
        Member member = Member.builder()
                .name(request.name())
                .nickName(request.nickname())
                .age(request.age())
                .sopt(request.sopt())
                .build();
        return memberJpaRepository.save(member);
    }

    @Transactional
    public void deleteMember(Long memberId) {
        Member member = memberJpaRepository.findByIdOrThrow(memberId);
        memberJpaRepository.delete(member);
    }

    @Transactional
    public Member updateMember(MemberCreateRequest request, Long memberId) {
        Member member = memberJpaRepository.findByIdOrThrow(memberId);
        member.setName(request.name());
        member.setNickName(request.nickname());
        member.setAge(request.age());
        member.setSopt(request.sopt());
        return memberJpaRepository.save(member);
    }

    private  Member findById(Long memberId) {
        return memberJpaRepository.findById(memberId).orElseThrow(
                () -> new EntityNotFoundException("존재하는 회원이 없습니다")
        );
    }
}
