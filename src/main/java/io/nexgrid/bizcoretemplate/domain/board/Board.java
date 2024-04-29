package io.nexgrid.bizcoretemplate.domain.board;

import io.nexgrid.bizcoretemplate.domain.member.Member;
import io.nexgrid.bizcoretemplate.domain.BaseEntity;
import io.nexgrid.bizcoretemplate.domain.board.enums.BoardStatus;
import io.nexgrid.bizcoretemplate.domain.board.enums.Category;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Board extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private Category category;
    private String title;
    private String contents;
    @ManyToOne(fetch = FetchType.LAZY)
    private Member writer;
    @Enumerated(EnumType.STRING)
    private BoardStatus boardStatus;
}