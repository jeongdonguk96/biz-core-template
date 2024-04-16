package io.nexgrid.bizcoretemplate.domain.member.repository;

import io.nexgrid.bizcoretemplate.domain.member.Member;
import io.nexgrid.bizcoretemplate.domain.member.dto.JoinRequestDto;

public interface MemberRepositoryCustom {

    Member getMemberByLogin(JoinRequestDto requestDto);

}
