package com.project.eatTogether.application.service;

import com.project.eatTogether.domain.entity.User;
import com.project.eatTogether.infrastructure.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EntityValidationService {

    private final UserRepository memberRepository;

    // Member 검증
    public User validateMember(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 회원입니다."));
    }

    // Member Email로 검증
    public User validateMemberByEmail(String userEmail) {
        return memberRepository.findByUserEmail(userEmail)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));
    }

    // 동일 이메일 검증
    public boolean existEmail(String userEmail) {
        return memberRepository.existsByUserEmail(userEmail);
    }

}