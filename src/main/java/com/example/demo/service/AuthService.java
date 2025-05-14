package com.example.demo.service;

import com.example.demo.domain.member.Member;
import com.example.demo.dto.auth.SignupRequest;
import com.example.demo.exception.Exception;
import com.example.demo.repository.MemberRepository;
import com.example.demo.security.JwtTokenProvider;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtTokenProvider jwtTokenProvider;
    private final MemberRepository memberRepository;

    public String login(String username, String password) {
        Member member = memberRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(Exception.MEMBER_NOT_FOUND_EXCEPTION.getMessage()));

        if (!member.getPassword().equals(password)) {
            throw new BadCredentialsException(Exception.PASSWORD_NOT_MATCH_EXCEPTION.getMessage());
        }

        return jwtTokenProvider.generateToken(member.getId().toString());
    }

    public void signup(SignupRequest request) {
        Optional<Member> existingMember = memberRepository.findByUsername(request.getUsername());
        if (existingMember.isPresent()) {
            throw new IllegalArgumentException(Exception.SIGNUP_USERNAME_DUPLICATE_EXCEPTION.getMessage());
        }

        Member member = new Member(
                request.getUsername(),
                request.getPassword(),
                request.getEmail()
        );

        memberRepository.save(member);
    }

    public boolean isLogin(HttpServletRequest request) {
        String token = jwtTokenProvider.resolveToken(request);

        return token != null && jwtTokenProvider.validateToken(token);
    }
}
