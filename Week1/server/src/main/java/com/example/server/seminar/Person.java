package com.example.server.seminar;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
//@AllArgsConstructor
//@RequiredArgsConstructor
public class Person {
    private String lastName;
    private String firstName;

    @Builder
    public Person(String lastName, String firstName) {
        this.lastName = lastName;
        this.firstName = firstName;
    }
}
