package com.base.servlet.web.frontcontroller.v4.controller;

import com.base.servlet.domain.member.Member;
import com.base.servlet.domain.member.MemberRepository;
import com.base.servlet.web.frontcontroller.ModelView;
import com.base.servlet.web.frontcontroller.v4.ControllerV4;

import java.util.Map;

public class MemberSaveControllerV4 implements ControllerV4 {

    private final MemberRepository memberRepository = MemberRepository.getInstance();


    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) {
        // 1. paramMap에서 데이터를 읽어옴
        String username = paramMap.get("username");
        int age = Integer.parseInt(paramMap.get("age"));

        // 2. Member 객체를 생성 후 저장
        Member member = new Member(username, age);
        memberRepository.save(member);

        // 3. ModelView 객체를 model에 저장 후 반환
        model.put("member", member);
        return "save-result";
    }
}
