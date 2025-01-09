package com.ll.chatApp.domain.member.repository;

import com.ll.chatApp.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
