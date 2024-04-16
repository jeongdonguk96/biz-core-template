package io.nexgrid.bizcoretemplate.domain.member;

import io.nexgrid.bizcoretemplate.domain.member.dto.JoinRequestDto;
import io.nexgrid.bizcoretemplate.domain.member.repository.RedisRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final RedisRepository redisRepository;


    @PostMapping("/test")
    public void test(HttpServletRequest servletRequest, @RequestBody JoinRequestDto request) {
        System.out.println("request = " + request);
        memberService.join(request);
        Member member = memberService.login(servletRequest, request);
        Optional<String> allSession = redisRepository.findAllSession();

        System.out.println("username = " + member.getUsername());
        System.out.println("password = " + member.getPassword());
    }

}
