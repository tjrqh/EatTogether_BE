package com.project.eatTogether.application.service.differed;

import com.project.eatTogether.domain.entity.differed.Member;
import com.project.eatTogether.infrastructure.differed.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EntityValidationService {

    private final MemberRepository memberRepository;

    // Member 검증
    public Member validateMember(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 회원입니다."));
    }

    // Member Email로 검증
    public Member validateMemberByEmail(String email) {
        return memberRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));
    }

    // 동일 이메일 검증
    public boolean existEmail(String email) {
        return memberRepository.existsByEmail(email);
    }

}