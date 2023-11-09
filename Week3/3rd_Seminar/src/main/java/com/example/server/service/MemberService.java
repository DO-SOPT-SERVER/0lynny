package com.example.server.service;

import com.example.server.domain.Member;
import com.example.server.dto.member.MemberCreateRequest;
import com.example.server.dto.member.MemberGetResponse;
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

    /*
        문제상황
        MemberId에 따라 조회를 하게 되는데 해당 Id가 없는 경우에 조회를 할 수 없으므로
        Member라는 갯체가 반환이 되지 않을 수도 있음
     */
    public MemberGetResponse getByIdV1(Long memberId) {
        //get()은 무지겅으로 가져옴
        //문제 : Null인데 가져올 수 있음 -> NullPointerException 발생
        Member member = memberJpaRepository.findById(memberId).get();
        // 팩토리 메서드를 사용
        MemberGetResponse response = MemberGetResponse.of(member);
        return response;
    }

    public MemberGetResponse getByIdV2(Long memberId) {
//        Member member = memberJpaRepository.findById(memberId)
//                //만약 오류가 발생하면 오류를 던진다.
//                .orElseThrow( () -> new EntityNotFoundException("해당하는 회원이 없습니다"));
//        return MemberGetResponse.of(member);
        return MemberGetResponse.of(memberJpaRepository.findByIdOrThrow(memberId));
    }

    public List<MemberGetResponse> getMembers() {
        // Member로 가져온 것을 Response로 바꿔주기 위함
//        List<MemberGetResponse> members = memberJpaRepository.findAll()
//                .stream()
//                .map(member -> MemberGetResponse.of(member))
//                .collect(Collectors.toList());
        //타입이 똑같으므로 바로 Reruen 때려도 된ㄱ
        return memberJpaRepository.findAll()
                .stream()
                .map(MemberGetResponse::of)
                .collect(Collectors.toList());
    }

    @Transactional
    public String create(MemberCreateRequest request) {
//        Member member = Member.builder()
//                .name(request.getName())
//                .nickName(request.getNickname())
//                .age(request.getAge())
//                .sopt(request.getSopt())
//                .build();
        Member member = Member.builder()
                .name(request.name())
                .nickname(request.nickname())
                .age(request.age())
                .sopt(request.sopt())
                .build();
        //저장한 애 문자열로 바꿔주고 그다음 URI로 바꿔준다.
        return memberJpaRepository.save(member).getId().toString();
    }

    //다른 방법으로는 직접 메소드를 만들어 줄 수도 있다.
    private  Member findById(Long memberId) {
        return memberJpaRepository.findById(memberId).orElseThrow(
                () -> new EntityNotFoundException("존재하는 회원이 없습니다")
        );
    }


}
