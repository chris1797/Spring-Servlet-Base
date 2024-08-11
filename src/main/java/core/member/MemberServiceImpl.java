package core.member;

public class MemberServiceImpl implements MemberService {

    // 구현 객체를 직접 선택
    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    // Test 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
