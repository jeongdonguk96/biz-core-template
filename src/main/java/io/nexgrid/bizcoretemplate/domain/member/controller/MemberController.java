package io.nexgrid.bizcoretemplate.domain.member.controller;

import io.nexgrid.bizcoretemplate.constant.ResultCode;
import io.nexgrid.bizcoretemplate.domain.member.dto.SignUpDto;
import io.nexgrid.bizcoretemplate.domain.member.service.MemberService;
import io.nexgrid.bizcoretemplate.dto.ResponseDto;
import io.nexgrid.bizcoretemplate.handler.exception.ApiParameterNotValidException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    /*
        ※ Spring Validation
        boot 3.1 버전 이상부터는 유연한 에러처리를 위해 @Valid에 대한 에외를 발생시키지 않음 -> Custom으로 처리
    */

    /*
        TODO 기능 추가 List
        - ID 체크 API
        - 멤버 정보 조회
        - 멤버 정보 삭제
        - 멤버 정보 변경
        - 로그인, 로그아웃
        - 세션정보 ???
     */

    @PostMapping("/signup")
    public ResponseEntity<ResponseDto<Object>> signUpRequest(@Validated @RequestBody SignUpDto signUpDTO,
                                                             BindingResult bindingResult) throws Exception {
        // Validation DTO에 명시 (Validation 실패시 예외 핸들러가 처리)
        log.info("▷▷▷ SignUp Request : {}", signUpDTO.toString());

        // TODO 필수파라미터 검증 추가

        // 파라미터 Validation 체크
        if (bindingResult.hasErrors()) {
             throw new ApiParameterNotValidException(bindingResult);
        }

        try {
            memberService.signUpProcess(signUpDTO);
        } catch (DataIntegrityViolationException ex) {
            // 이미 존재하는 계정일때
            log.info("DUPLICATE_ID : {} - 이미 존재하는 계정으로 가입요청", signUpDTO.getUserName());
            return ResponseEntity.badRequest().body(ResponseDto.resultSet(ResultCode.DUPLICATE_LOGIN_ID.getCode()
                                                                        , ResultCode.DUPLICATE_LOGIN_ID.getMessage()));
        }

        return ResponseEntity.ok(ResponseDto.resultSet(ResultCode.SUCCESS.getCode()
                                                    , ResultCode.SUCCESS.getMessage()));
    }



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

}



