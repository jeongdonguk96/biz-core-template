package io.nexgrid.bizcoretemplate.domain.member.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import io.nexgrid.bizcoretemplate.domain.member.Member;
import io.nexgrid.bizcoretemplate.domain.member.dto.JoinRequestDto;
import lombok.RequiredArgsConstructor;

import static io.nexgrid.bizcoretemplate.domain.member.QMember.member;

@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    @Override
    public Member getMemberByLogin(JoinRequestDto request) {
        return queryFactory
                .select(member)
                .from(member)
                .where(member.username.eq(request.username())
                        .and(member.password.eq(request.password()))
                )
                .fetchFirst();
    }
}
