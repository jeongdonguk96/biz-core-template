package io.nexgrid.bizcoretemplate.domain.member.controller;

import io.nexgrid.bizcoretemplate.domain.member.dto.SignUpDto;
import io.nexgrid.bizcoretemplate.domain.member.service.MemberService;
import io.nexgrid.bizcoretemplate.entity.ResultCode;
import io.nexgrid.bizcoretemplate.entity.ResultDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/members")
public class MemberController {

    private MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) { this.memberService = memberService; }
    // 생성자 주입방식

//    @GetMapping("")
//    public String getMemberInfo(Model model) {
//
//        String username = SecurityContextHolder.getContext().getAuthentication().getName();
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
//        Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
//        GrantedAuthority auth = iterator.next();
//        String role = auth.getAuthority();
//        // 세션정보(ID, Role) 가져오기
//
//        if (username == null) {
//            username = "null";
//        }
//
//        model.addAttribute("username", username);
//        model.addAttribute("role", role);
//
//        return "info";
//    }

    @PostMapping("/signup")
    public ResponseEntity<ResultDto<Object>> signUpRequest(@Validated @RequestBody SignUpDto signUpDTO,
                                        BindingResult bindingResult) throws Exception {
        // Validation DTO에 명시 (Validation 실패시 예외 핸들러가 처리)
        // application/json; charset=UTF-8 요청

        System.out.println("▷ SignUp Request : "+signUpDTO.toString());

        // TODO 필수파라미터 검증 추가

//        if(bindingResult.hasErrors()) {
//            // 파라미터 Validation 검증 실패시
//            for (FieldError error : bindingResult.getFieldErrors()) {
//                System.out.println("### Validation Error : {"+error.getField()+","+error.getDefaultMessage()+"}");
//            }
//            // throw new MethodArgumentNotValidException(bindingResult);
//        }
        // TODO - Validation 에러시 예외처리 안해도 핸들러가 처리하는지 테스트 해야함

        memberService.signUpProcess(signUpDTO);

        return ResponseEntity.ok(ResultDto.resultSet(ResultCode.SUCCESS.getCode()
                                                    , ResultCode.SUCCESS.getMessage()));
    }


}
