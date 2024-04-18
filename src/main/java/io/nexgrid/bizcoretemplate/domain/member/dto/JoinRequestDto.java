package io.nexgrid.bizcoretemplate.domain.member.dto;

import io.nexgrid.bizcoretemplate.domain.member.Member;

public record JoinRequestDto(
        String email,
        String password
) {

    public Member toEntity(Member member) {
        return Member.builder()
                .email(email())
                .password(password())
                .build();
    }

}
