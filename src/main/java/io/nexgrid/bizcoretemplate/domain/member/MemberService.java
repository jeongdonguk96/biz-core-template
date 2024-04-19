package io.nexgrid.bizcoretemplate.domain.member;

import io.nexgrid.bizcoretemplate.domain.member.dto.SignUpDTO;
import io.nexgrid.bizcoretemplate.domain.member.enums.Role;
import io.nexgrid.bizcoretemplate.domain.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    private MemberRepository memberRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public void signUpProcess(SignUpDTO signUpDTO) {

        Member member = Member.builder()
                .email(signUpDTO.getEmail())
                .password(bCryptPasswordEncoder.encode(signUpDTO.getPassword()))
                .role(Role.ROOT)
                .build();

        memberRepository.save(member);
    }

    public List<Member> selectList() {

        return memberRepository.findAll();
    }


}
