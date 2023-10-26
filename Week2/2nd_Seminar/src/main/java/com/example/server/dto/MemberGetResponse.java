package com.example.server.dto;

import com.example.server.domain.Member;
import com.example.server.domain.SOPT;
import lombok.AllArgsConstructor;
import lombok.Data;

public record MemberGetResponse(
        String name,
        String nickname,
        int age,
        SOPT sopt
) {
    public static MemberGetResponse of(Member member) {
        return new MemberGetResponse(
                member.getName(),
                member.getNickName(),
                member.getAge(),
                member.getSopt()
        );
    }
}