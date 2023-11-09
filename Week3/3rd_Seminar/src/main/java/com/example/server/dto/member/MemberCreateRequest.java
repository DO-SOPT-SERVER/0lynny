package com.example.server.dto.member;

import com.example.server.domain.SOPT;
import lombok.Data;

//@Data
//public class MemberCreateRequest {
//    private String name;
//    private String nickname;
//    private int age;
//    private SOPT sopt;
//}

public record MemberCreateRequest(
        String name,
        String nickname,
        int age,
        SOPT sopt
) {
}

/*
레코드를 이용한 리팩초링
 */