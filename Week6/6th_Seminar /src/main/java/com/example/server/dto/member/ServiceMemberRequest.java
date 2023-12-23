package com.example.server.dto.member;

public record ServiceMemberRequest(
        String nickname,
        String password
) {
}