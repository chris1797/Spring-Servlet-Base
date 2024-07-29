package core.discount;

import core.member.Grade;
import core.member.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RateDiscountPolicyTest {

    RateDiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("VIP는 10% 할인이 적용되는지 테스트")
    void discount() {
        // given
        Member member = new Member(1L, "memberA", Grade.VIP);

        // when
        int discount = discountPolicy.discount(member, 10000);

        // then
        assertEquals(discount, 1000);
    }

    @Test
    @DisplayName("VIP가 아닌 경우 할인이 적용되지 않는지 테스트")
    void discount2() {
        // given
        Member member = new Member(2L, "memberB", Grade.BASIC);

        // when
        int discount = discountPolicy.discount(member, 10000);

        // then
        assertEquals(discount, 0);
    }
}