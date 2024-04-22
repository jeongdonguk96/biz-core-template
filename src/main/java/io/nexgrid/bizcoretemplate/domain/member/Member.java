package io.nexgrid.bizcoretemplate.domain.member;

import io.nexgrid.bizcoretemplate.domain.board.Board;
import io.nexgrid.bizcoretemplate.entity.BaseEntity;
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
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;
    private String username;
    private String domain;
    private String birth;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String oldPassword1;
    private String oldPassword2;
    private Integer loginFailCount;
    @Enumerated(EnumType.STRING)
    private Role role;
    @Enumerated(EnumType.STRING)
    private UserStatus userStatus;
    private String passwordModifiedDate;
    private String lastLoginDate;
    @OneToMany(mappedBy = "writer", fetch = FetchType.LAZY)
    private List<Board> boardList = new ArrayList<>();
}