package io.nexgrid.bizcoretemplate.domain.member;

import io.nexgrid.bizcoretemplate.domain.member.dto.JoinRequestDto;
import io.nexgrid.bizcoretemplate.domain.member.repository.MemberRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public void join(JoinRequestDto request) {
        Member newMember = request.toEntity(new Member());
        memberRepository.save(newMember);
    }

    @Transactional
    public Member login(HttpServletRequest servletRequest, JoinRequestDto request) {
        HttpSession session = servletRequest.getSession();
        Member member = memberRepository.getMemberByLogin(request);
        session.setAttribute("mid", member);

        return member;
    }

}
