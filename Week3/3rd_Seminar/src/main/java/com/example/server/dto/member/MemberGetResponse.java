package com.example.server.dto.member;

import com.example.server.domain.Member;
import com.example.server.domain.SOPT;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MemberGetResponse {
    private String name;
    private String nickname;
    private int age;
    private SOPT sopt;

    public static MemberGetResponse of(Member member) {
        return new MemberGetResponse(
                member.getName(),
                member.getNickname(),
                member.getAge(),
                member.getSopt()
        );
    }
}
