package io.nexgrid.bizcoretemplate.domain.member.dto;

import io.nexgrid.bizcoretemplate.domain.member.Member;
import io.nexgrid.bizcoretemplate.domain.member.enums.Gender;
import io.nexgrid.bizcoretemplate.domain.member.enums.Role;
import io.nexgrid.bizcoretemplate.domain.member.enums.UserStatus;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
public class SignUpDTO {

    /*
        Validation 어노테이션 문서
        https://docs.jboss.org/hibernate/stable/validator/reference/en-US/html_single/#section-builtin-constraints
    */
    @Max(20) @NotBlank
    private String username; // 유저아이디
    @NotBlank
    private String password; // 비밀번호
    @NotBlank
    private String name; // 성명
    @NotBlank
    private String birth; // 생년월일 (YYYY-MM-DD)
    @NotBlank
    private Gender gender; // 성별 (MALE-남, FEMALE-여, NONE-식별불가)
    @NotBlank
    private Role role; // 유저 권한 (NORMAL-유저, ROOT-관리자)
    @NotBlank
    private String joinDate; // 가입날짜 (YYYY-MM-DD hh:mm:ss)

    public Member signUpEntity() {
        return Member.builder()
                .username(username)
                .password(password)
                .name(name)
                .birth(birth)
                .gender(gender)
                .role(role)
                .joinDate(joinDate)
                .build();
    }
}
