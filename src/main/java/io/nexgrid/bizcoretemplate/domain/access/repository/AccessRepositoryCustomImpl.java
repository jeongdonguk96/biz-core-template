package io.nexgrid.bizcoretemplate.domain.access.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class AccessRepositoryCustomImpl implements AccessRepositoryCustom {

    private final MongoTemplate mongoTemplate;

    // 몽고DB에서 accessResource를 distinct로 가져온다.
    @Override
    public List<String> findDistinctAccessResource() {
        return mongoTemplate.getCollection("access")
                .distinct("accessResource", String.class)
                .into(new ArrayList<>());
    }

}
