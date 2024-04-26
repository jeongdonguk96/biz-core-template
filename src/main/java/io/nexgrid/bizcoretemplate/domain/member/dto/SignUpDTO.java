package io.nexgrid.bizcoretemplate.domain.member.dto;

import io.nexgrid.bizcoretemplate.domain.member.enums.Gender;
import io.nexgrid.bizcoretemplate.domain.member.enums.Role;
import io.nexgrid.bizcoretemplate.domain.member.enums.UserStatus;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SignUpDTO {

    private String username; // 유저아이디
    private String password; // 비밀번호
    private String name; // 성명
    private String birth; // 생년월일 (YYYY-MM-DD)
    private Gender gender; // 성별 (F-여자, M-남자)
    private Role role; // 유저 권한 (NORMAL-유저, ROOT-관리자)
    private UserStatus userStatus; // 유저 상태 (ACTIVE-활동, IN_ACTIVE-휴먼, LOCKED-잠김)
    private String joinDate; // 가입날짜 (YYYY-MM-DD hh:mm:ss)
}
