package io.nexgrid.bizcoretemplate.domain.member;

import io.nexgrid.bizcoretemplate.domain.member.dto.JoinRequestDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;


    @PostMapping("/test")
    public void test(HttpServletRequest servletRequest, @RequestBody JoinRequestDto request) {
        System.out.println("request = " + request);
        memberService.join(request);
        Member member = memberService.login(servletRequest, request);

        System.out.println("username = " + member.getUsername());
        System.out.println("password = " + member.getPassword());
    }

    @PostMapping("/test2")
    public void test2(HttpServletRequest servletRequest, @RequestBody JoinRequestDto request) {
        HttpSession session = servletRequest.getSession();
        session.getAttribute("member");
    }

}
