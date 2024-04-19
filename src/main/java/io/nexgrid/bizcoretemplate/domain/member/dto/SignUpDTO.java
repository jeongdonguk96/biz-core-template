package io.nexgrid.bizcoretemplate.domain.member.dto;

import io.nexgrid.bizcoretemplate.domain.member.enums.Role;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SignUpDTO {

    private String email;
    private String password;
    private Role role;
}
