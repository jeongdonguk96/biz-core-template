package io.nexgrid.bizcoretemplate.domain.member.dto;

import io.nexgrid.bizcoretemplate.domain.member.Member;

public record JoinRequestDto(
        String username,
        String password
) {

    public Member toEntity(Member member) {
        return Member.builder()
                .username(username())
                .password(password())
                .build();
    }

}
