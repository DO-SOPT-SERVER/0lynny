package com.example.server.dto.request;

import com.example.server.domain.SOPT;
import lombok.Data;

public record MemberCreateRequest(
        String name,
        String nickname,
        int age,
        SOPT sopt
) {}

