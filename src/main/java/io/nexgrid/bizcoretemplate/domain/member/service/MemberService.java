package io.nexgrid.bizcoretemplate.domain.member.service;

import io.nexgrid.bizcoretemplate.domain.member.Member;
import io.nexgrid.bizcoretemplate.domain.member.dto.SignUpDto;
import io.nexgrid.bizcoretemplate.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public void signUpProcess(SignUpDto signUpDto) {

        Member member = signUpDto.signUpEntity();

        memberRepository.save(member);
    }

    public List<Member> selectList() {

        return memberRepository.findAll();
    }


}
