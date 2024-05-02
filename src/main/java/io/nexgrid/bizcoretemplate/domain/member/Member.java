package io.nexgrid.bizcoretemplate.domain.member;

import io.nexgrid.bizcoretemplate.domain.BaseEntity;
import io.nexgrid.bizcoretemplate.domain.board.Board;
import io.nexgrid.bizcoretemplate.domain.member.enums.Gender;
import io.nexgrid.bizcoretemplate.domain.member.enums.Role;
import io.nexgrid.bizcoretemplate.domain.member.enums.UserStatus;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Builder
@ToString(exclude = "boardList")
@NoArgsConstructor
@AllArgsConstructor
public class Member extends BaseEntity implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) // 시퀀스 자동증가 (DB 따라감)
    private Long id; // Sequence = Primary Key

    @Column(length = 20, unique = true)
    private String username; // 유저아이디 (security로 통일)

    @Column(nullable = false)
    private String password; // 비밀번호

    @Column(nullable = false)
    private String name; // 성명

    private String domain; // 도메인명 (접속기록용)

    @Column(nullable = false)
    private String birth; // 생년월일 (YYYY-MM-DD)

    @Enumerated(EnumType.STRING) @Column(nullable = false)
    private Gender gender; // 성별 (MALE-남, FEMALE-여, NONE-식별불가)

    private String oldPassword1; // 이전비밀번호1
    private String oldPassword2; // 이전비밀번호2

    private Integer loginFailCount; // 로그인 실패횟수

    @Enumerated(EnumType.STRING) @Column(nullable = false)
    private Role role; // 유저 권한 (NORMAL-유저, ROOT-관리자)

    @Enumerated(EnumType.STRING) @Column(nullable = false)
    private UserStatus userStatus; // 유저 상태 (ACTIVE-활동, IN_ACTIVE-휴먼, LOCKED-잠김)

    private String passwordModifiedDate; // 비밀번호 변경날짜

    private String lastLoginDate; // 마지막 로그인 날짜

    @OneToMany(mappedBy = "writer", fetch = FetchType.LAZY)
    private List<Board> boardList = new ArrayList<>(); // 작성 게시물
}