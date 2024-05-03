package io.nexgrid.bizcoretemplate.domain.member.dto;

import io.nexgrid.bizcoretemplate.domain.member.Member;
import io.nexgrid.bizcoretemplate.domain.member.enums.Gender;
import io.nexgrid.bizcoretemplate.domain.member.enums.Role;
import io.nexgrid.bizcoretemplate.domain.member.enums.UserStatus;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Data
public class SignUpDto {

    @Max(20) @NotBlank
    private String userName; // 유저아이디

    @NotBlank(message = "값이 올바르지 않습니다.")
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
                //TODO IllegalArgumentException을 던지기 때문에 Validator 커스텀해야함
                .role(Role.valueOf(role))
                .userStatus(UserStatus.ACTIVE)
                .build();
    }

}
