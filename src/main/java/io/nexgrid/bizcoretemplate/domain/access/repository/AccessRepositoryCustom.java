package io.nexgrid.bizcoretemplate.domain.access.repository;

import java.util.List;

public interface AccessRepositoryCustom {

    List<String> findDistinctAccessResource();
}
