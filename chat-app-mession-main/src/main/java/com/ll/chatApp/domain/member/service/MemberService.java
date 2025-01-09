package com.ll.chatApp.domain.member.service;

import com.ll.chatApp.domain.member.entity.Member;
import com.ll.chatApp.domain.member.repository.MemberRepository;
import com.ll.chatApp.global.rsData.RsData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public RsData<Member> join(String userName, String password){
        Member member = Member.builder()
                .username(userName)
                .password(password)
                .build();

        memberRepository.save(member);

        return RsData.of("200", "%s님 가입을 환영합니다.".formatted(userName), member);
    }

    public Optional<Member> findById(long Id) {
        return memberRepository.findById(Id);
    }
}
