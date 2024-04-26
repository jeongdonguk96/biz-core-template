package io.nexgrid.bizcoretemplate.domain.member.controller;

import io.nexgrid.bizcoretemplate.domain.member.dto.SignUpDTO;
import io.nexgrid.bizcoretemplate.domain.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Iterator;

@RestController
@RequestMapping("/members")
public class MemberController {

    private MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
    // 생성자 주입방식


    @GetMapping("")
    public String getMemberInfo(Model model) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
        GrantedAuthority auth = iterator.next();
        String role = auth.getAuthority();
        // 세션정보(ID, Role) 가져오기

        if (username == null) {
            username = "null";
        }

        model.addAttribute("username", username);
        model.addAttribute("role", role);

        return "info";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }


    @GetMapping("/signup")
    public String signUpPage() {
        return "signup";
    }

    @PostMapping("/signup")
    public String signUpRequest(@RequestBody SignUpDTO signUpDTO) {
        // application/json; charset=UTF-8 요청

        System.out.println("#### SignUp Request : "+signUpDTO.toString());
        memberService.signUpProcess(signUpDTO);

        return "redirect:/members/login";
    }


}
