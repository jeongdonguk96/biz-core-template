package io.nexgrid.bizcoretemplate.domain.board.repository;

import io.nexgrid.bizcoretemplate.domain.board.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long>, BoardRepositoryCustom {
}
