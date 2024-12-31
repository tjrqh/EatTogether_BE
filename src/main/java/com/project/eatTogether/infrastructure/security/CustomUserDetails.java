package com.project.eatTogether.infrastructure.security;

import com.project.eatTogether.domain.entity.User;
import com.project.eatTogether.domain.enums.UserStatus;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Getter
@RequiredArgsConstructor
public class CustomUserDetails implements UserDetails {
    private final User user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // role의 key 값을 사용하여 권한 생성 (ROLE_ prefix가 포함된 값)
        return Collections.singleton(new SimpleGrantedAuthority(user.getUserRole().getKey()));
    }

    @Override
    public String getPassword() {
        return user.getUserPw();
    }

    @Override
    public String getUsername() {
        return user.getUserEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        // 계정 만료 여부 확인
        return !UserStatus.INACTIVE.equals(user.getUserStatus());
    }

    @Override
    public boolean isAccountNonLocked() {
        // 계정 잠금 여부 확인
        return !UserStatus.SUSPENDED.equals(user.getUserStatus());
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // 인증 정보 만료 여부 확인
        return true;
    }

    @Override
    public boolean isEnabled() {
        // 계정 활성화 여부 확인
        return UserStatus.ACTIVE.equals(user.getUserStatus());
    }

    // 사용자의 실제 권한 확인을 위한 편의 메서드
    public boolean isOwner() {
        return user.getUserRole().getKey().equals("ROLE_OWNER");
    }

    public boolean isAdmin() {
        return user.getUserRole().getKey().equals("ROLE_ADMIN");
    }

    public boolean isUser() {
        return user.getUserRole().getKey().equals("ROLE_USER");
    }
}