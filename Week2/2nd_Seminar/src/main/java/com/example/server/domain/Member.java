package com.example.server.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String nickName;
    private int age;
    @Embedded
    private SOPT sopt;
    @Builder
    public Member(String name, String nickName, int age, SOPT sopt) {
        this.name = name;
        this.nickName = nickName;
        this.age = age;
        this.sopt = sopt;
    }
}
