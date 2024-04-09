package io.nexgrid.bizcoretemplate.domain.member;

import io.nexgrid.bizcoretemplate.domain.board.Board;
import io.nexgrid.bizcoretemplate.entity.BaseEntity;
import io.nexgrid.bizcoretemplate.enums.Gender;
import io.nexgrid.bizcoretemplate.enums.Role;
import io.nexgrid.bizcoretemplate.enums.UserStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Member extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String email;
    private String domain;
    private String birth;
    private Gender gender;
    private String oldPassword1;
    private String oldPassword2;
    private Integer loginFailCount;
    private Role role;
    private UserStatus userStatus;
    private String passwordModifiedDate;
    private String lastLoginDate;
    @OneToMany(mappedBy = "writer", fetch = FetchType.LAZY)
    private List<Board> boardList = new ArrayList<>();
}
