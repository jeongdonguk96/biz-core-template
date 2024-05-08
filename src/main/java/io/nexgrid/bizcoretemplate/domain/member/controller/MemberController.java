package io.nexgrid.bizcoretemplate.domain.member.controller;

import io.nexgrid.bizcoretemplate.constant.ResultCode;
import io.nexgrid.bizcoretemplate.domain.member.Member;
import io.nexgrid.bizcoretemplate.domain.member.dto.SignUpDto;
import io.nexgrid.bizcoretemplate.domain.member.service.MemberService;
import io.nexgrid.bizcoretemplate.dto.ResponseDto;
import io.nexgrid.bizcoretemplate.handler.exception.ApiParameterNotValidException;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Iterator;

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
        - ID 중복 체크
        - 멤버 정보 변경
        - 로그인, 로그아웃 (세션 - redis)
        - 요청, 응답에 대한 로그
     */

    @PostMapping("/signup")
    public ResponseEntity<ResponseDto<Object>> signUpRequest(@Validated @RequestBody SignUpDto signUpDTO,
                                                             BindingResult bindingResult) throws Exception {
        // Validation DTO에 명시 (Validation 실패시 예외 핸들러가 처리)
        log.info("▷▷▷ SignUp Request : {}", signUpDTO.toString());

        // 파라미터 Validation 체크
        if (bindingResult.hasErrors()) {
             throw new ApiParameterNotValidException(bindingResult);
        }

        try {
            memberService.signUpProcess(signUpDTO);
        } catch (DataIntegrityViolationException ex) {
            log.info("### DUPLICATE_ID : {} - 이미 존재하는 계정으로 가입요청", signUpDTO.getUserName());
            return ResponseEntity.badRequest().body(ResponseDto.resultSet(ResultCode.DUPLICATE_ID.getCode()
                                                                        , ResultCode.DUPLICATE_ID.getMessage()));
        }

        return ResponseEntity.ok(ResponseDto.resultSet(ResultCode.SUCCESS.getCode()
                                                    , ResultCode.SUCCESS.getMessage()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto<Object>> getUserData(@Validated @Min(1) @PathVariable("id") Long id) throws Exception {

        log.info("▷▷▷ Get UserData Request - UserID Key: {}", id);
        Member member = memberService.getUserById(id);

        if (member == null) {
            log.info("### Member Not Found - 조회된 정보가 없습니다. UserID Key : {}", id);
            return ResponseEntity.ok(ResponseDto.resultSet(ResultCode.NOT_FOUND_DATA.getCode()
                                                        , ResultCode.NOT_FOUND_DATA.getMessage()));
        }

        return ResponseEntity.ok(ResponseDto.resultSet(ResultCode.SUCCESS.getCode()
                                                    , ResultCode.SUCCESS.getMessage()
                                                    , member));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto<Object>> deleteUserData(@Validated @Min(1) @PathVariable("id") Long id) throws Exception {

        log.info("▷▷▷ Delete UserData Request - UserID Key: {}", id);

        boolean result = memberService.deleteById(id);

        if (!result) {
            log.info("### Member Not Found - 존재하지 않는 사용자입니다. UserID Key : {}", id);
            return ResponseEntity.ok(ResponseDto.resultSet(ResultCode.MEMBER_NOT_FOUND.getCode()
                                                        , ResultCode.MEMBER_NOT_FOUND.getMessage()));
        }

        return ResponseEntity.ok(ResponseDto.resultSet(ResultCode.SUCCESS.getCode()
                                                    , ResultCode.SUCCESS.getMessage()));
    }


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

}



