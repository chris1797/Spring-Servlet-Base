package core.member;

import org.junit.jupiter.api.Test;

public class MemberServiceTest {

    @Test
    void join() {
        // given
        MemberService memberService = new MemberServiceImpl();
        Member member = new Member(1L, "memberA", Grade.VIP);

        // when
        memberService.join(member);

        // then
        Member findMember = memberService.findMember(1L);
        org.assertj.core.api.Assertions.assertThat(member).isEqualTo(findMember);
    }
}
