package io.nexgrid.bizcoretemplate.domain.board;

import io.nexgrid.bizcoretemplate.domain.member.Member;
import io.nexgrid.bizcoretemplate.entity.BaseEntity;
import io.nexgrid.bizcoretemplate.enums.BoardStatus;
import io.nexgrid.bizcoretemplate.enums.Category;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Board extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Category category;
    private String title;
    private String contents;
    @ManyToOne(fetch = FetchType.LAZY)
    private Member writer;
    private BoardStatus boardStatus;
}
