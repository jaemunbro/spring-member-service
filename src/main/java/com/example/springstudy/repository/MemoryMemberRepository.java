package com.example.springstudy.repository;
//구현체를 만든다.
import com.example.springstudy.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository {

    // 저장은 일단 map에다가 하자. key(id)는 long, 값은 member
    // 동시성 문제를 고려해서 concurrenthashmap을 써야하지만 여기서는 일단 hashmap
    private static Map<Long, Member> store = new HashMap<>();
    // long도 어텀롱? 동시성을 고려해야하지만 여기서는 일단 그냥 쓴다.
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        // 그냥 store.get(id) 만 줘도 되는데,
        // null이 반환될 가능성이 있으면 Optional.ofNullabe  로 반환해준다.
        // 그럼 client에서 할 수 있는게 좀 더 생김
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))  // lambda
                .findAny(); // 룹 돌다가 하나라도 뜨면
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
