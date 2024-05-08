package io.nexgrid.bizcoretemplate.domain.member.service;

import io.nexgrid.bizcoretemplate.domain.member.Member;
import io.nexgrid.bizcoretemplate.domain.member.dto.SignUpDto;
import io.nexgrid.bizcoretemplate.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public void signUpProcess(SignUpDto signUpDto) {

        signUpDto.setPassWord(bCryptPasswordEncoder.encode(signUpDto.getPassWord()));
        Member member = memberRepository.save(signUpDto.signUpEntity());
        log.info("### SignUp Success : {} ", member.toString());
    }

    public List<Member> selectList() {

        return memberRepository.findAll();
    }

    public Member getUserById(Long id) {

        Member member = null;
        Optional<Member> optionalMember = memberRepository.findById(id);

        if (optionalMember.isPresent()) {
            member = optionalMember.get();
            log.info("### Get UserData Success : {}", member.toString());
        }

        return member;
    }


    public boolean deleteById(Long id) {

        Optional<Member> optionalMember = memberRepository.findById(id);

        if (optionalMember.isPresent()) {
            Member member = optionalMember.get();
            memberRepository.deleteById(id);
            log.info("### Delete UserData Success : {}", member.toString());
            return true;
        }
        return false;
    }
}
