package core;

import core.discount.DiscountPolicy;
import core.discount.FixDiscountPolicy;
import core.discount.RateDiscountPolicy;
import core.member.MemberService;
import core.member.MemberServiceImpl;
import core.member.MemoryMemberRepository;
import core.order.OrderService;
import core.order.OrderServiceImpl;

public class AppConfig {

    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    private MemoryMemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    private DiscountPolicy discountPolicy() {
        return new FixDiscountPolicy();
//        return new RateDiscountPolicy();
    }
}
