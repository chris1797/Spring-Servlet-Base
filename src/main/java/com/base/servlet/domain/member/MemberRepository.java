package com.base.servlet.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * product level 에서 ConcurrentHashMap, AtomicLong 고려
 * <p>
 * 싱글톤으로 구현한 클래스
 * <p>
 * Spring에서는 싱글톤을 보장해줌
 */
public class MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

//    MemberRepository 단일 instance
    private static final MemberRepository instance = new MemberRepository();

    public static MemberRepository getInstance() {
        return instance;
    }

    private MemberRepository() {
//    private 생성자로, 싱글톤을 위해 외부에서 instance를 생성하지 못하도록 하기 위함
    }

    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    public Member findById(Long id) {
        return store.get(id);
    }

    public List<Member> findAll() {
//        store의 모든 값들로 새로운 ArrayList를 생성해 return
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
//        store 비우기
        store.clear();
    }


}
