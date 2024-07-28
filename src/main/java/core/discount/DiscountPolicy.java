package core.discount;

import core.member.Member;

public interface DiscountPolicy {

    /**
    * @param price 할인 대상 금액
    * @return 할인 대상 금액
    */
    int discount(Member member, int price);
}
