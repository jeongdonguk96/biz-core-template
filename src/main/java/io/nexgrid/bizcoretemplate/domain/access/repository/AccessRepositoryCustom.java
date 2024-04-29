package io.nexgrid.bizcoretemplate.domain.access.repository;

import java.util.List;

public interface AccessRepositoryCustom {

    List<String> findDistinctAccessResource();

    Integer countHourlyAccessByAccessResource(String accessResource, String accessDay, String accessHour);
}
