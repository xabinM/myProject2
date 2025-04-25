package com.example.demo.service;

import com.example.demo.domain.member.Member;
import com.example.demo.dto.SignUpResponse;
import com.example.demo.dto.SignupRequest;
import com.example.demo.repository.MemberRepository;
import com.example.demo.security.JwtTokenProvider;
import jakarta.validation.Valid;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final JwtTokenProvider jwtTokenProvider;
    private final MemberRepository memberRepository;

    public AuthService(JwtTokenProvider jwtTokenProvider, MemberRepository memberRepository) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.memberRepository = memberRepository;
    }

    public String login(String username, String password) {
        Member member = memberRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));

        if (!member.getPassword().equals(password)) {
            throw new BadCredentialsException("비밀번호가 일치하지 않습니다.");
        }

        return jwtTokenProvider.generateToken(member.getId().toString());
    }

    public void signup(SignupRequest request) {
        Optional<Member> existingMember = memberRepository.findByUsername(request.getUsername());
        if (existingMember.isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 아이디입니다.");
        }

        Member member = new Member();

        member.setUsername(request.getUsername());
        member.setPassword(request.getPassword());
        member.setEmail(request.getEmail());

        memberRepository.save(member);
    }
}
