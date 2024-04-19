package io.nexgrid.bizcoretemplate.domain.member;

import io.nexgrid.bizcoretemplate.domain.member.dto.SignUpDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {

    private MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
    // 생성자 주입방식

    @GetMapping("/")
    public String test() {
        return "test";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/signup")
    public String signUpPage() {

        return "signup";
    }

    @PostMapping("/signupRequest")
    public String signUpRequest(SignUpDTO signUpDTO) {

        System.out.println("### UserEmail : "+signUpDTO.getEmail());
        System.out.println("### Password : "+signUpDTO.getPassword());

        memberService.signUpProcess(signUpDTO);

        return "redirect:/login";
    }

    @GetMapping("/list")
    public String memberList() {

        List<Member> memberList = memberService.selectList();
        for (Member member: memberList) {
            System.out.println("MemberInfo : "+member.toString());
        }

        return "list";
    }
}
