package io.nexgrid.bizcoretemplate.domain.member.dto;

import io.nexgrid.bizcoretemplate.domain.member.Member;
import io.nexgrid.bizcoretemplate.domain.member.enums.Gender;
import io.nexgrid.bizcoretemplate.domain.member.enums.Role;
import io.nexgrid.bizcoretemplate.domain.member.enums.UserStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SignUpDto {

    @NotBlank @Size(max = 20, message = "USER ID는 20글자 이하입니다.")
    private String userName; // 유저아이디

    @NotBlank
    private String passWord; // 비밀번호

    @NotBlank
    private String name; // 성명

    @NotBlank
    private String birth; // 생년월일 (YYYYMMDD)

    @NotBlank
    private String gender; // 성별 (MALE-남, FEMALE-여, NONE-식별불가)

    @NotBlank
    private String role; // 유저 권한 (NORMAL-유저, ROOT-관리자)

    public Member signUpEntity() {
        return Member.builder()
                .username(userName)
                .password(passWord)
                .name(name)
                .birth(birth)
                .gender(Gender.valueOf(gender))
                //TODO enum 클래스는 IllegalArgumentException을 던지기 때문에 Validator 커스텀해야함
                .role(Role.valueOf(role))
                .userStatus(UserStatus.ACTIVE)
                .build();
    }

}
