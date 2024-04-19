package io.nexgrid.bizcoretemplate.domain.access.repository;

import io.nexgrid.bizcoretemplate.domain.access.Access;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AccessRepository {

    private final MongoTemplate mongoTemplate;

    // 몽고DB에 도큐먼트를 저장한다.
    public void save(Access access) {
        mongoTemplate.insert(access);
    }


    // 몽고DB에서 accessResource를 distinct로 가져온다.
    public List<String> findDistinctAccessResource() {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.group("accessResource")
        );

        AggregationResults<String> results = mongoTemplate.aggregate(
                aggregation, "access", String.class
        );

        return results.getMappedResults();
    }


    // 몽고DB에서 accessResource 별 접근 수를 가져온다.
    public Integer countAccessByAccessResource(String accessResource, String accessDay, String accessHour) {
        return (int) mongoTemplate.count(
                Query.query(Criteria.where("accessResource").is(accessResource)
                        .and("accessDay").is(accessDay)
                        .and("accessHour").is(accessHour)),
                Integer.class
        );
    }

}
