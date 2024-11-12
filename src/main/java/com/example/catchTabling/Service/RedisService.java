package com.example.catchTabling.Service;

import com.example.catchTabling.Model.Entity.Member;
import com.example.catchTabling.Repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RedisService {
    @Autowired
    private final MemberRepository memberRepository;

    public RedisService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public void setRedisData() {
        Member member = new Member(1L, "도환");
        memberRepository.save(member);
    }
}