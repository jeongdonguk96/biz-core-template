package io.nexgrid.bizcoretemplate.domain.board.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BoardRepositoryImpl implements BoardRepositoryCustom{

    private final JPAQueryFactory jpaQueryFactory;

}
