package com.base.servlet.domain.member;

import core.member.Grade;
import lombok.Data;

@Data
public class Member {

    private Long id;
    private String username;
    private int age;

    public Member(String username, int age) {
        this.username = username;
        this.age = age;
    }
}
