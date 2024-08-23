package core.order;

import core.annotation.MainDiscountPolicy;
import core.discount.DiscountPolicy;
import core.member.Member;
import core.member.MemberRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    /*
    필드 주입이 권장되지 않는 이유
    - 변경이 불가능하다.
    - 필요한 빈들과의 의존관계 주입이 불가능하기 때문에 테스트 코드 작성이 어렵다.
     */

    @Getter
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    /**
     * 생성자 주입의 의존관계 주입 시점은 해당 빈의 생성 시점(생성자 호출 시점)에 스프링이 알아서 주입해준다.
     */
    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }


    /**
     * <p>수정자 주입의 의존관계 주입 시점은 해당 빈의 생성 시점 이후, 스프링 컨테이너가 관리중인 빈이 모두 생성되고, 의존관계 주입이 완료된 이후에 호출된다.</p>
     * 수정자 주입은 선택, 변경 가능성이 있는 의존관계에 사용한다.
     */
    @Autowired
    public void setMemberRepository(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
    }


    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);

        // 할인정책에 대한 책임은 discountPolicy
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

}
