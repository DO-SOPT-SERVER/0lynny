package com.example.server.dto.member;

import com.example.server.domain.SOPT;
import lombok.Data;

public record MemberCreateRequest(
        String name,
        String nickname,
        int age,
        SOPT sopt
) {
}
