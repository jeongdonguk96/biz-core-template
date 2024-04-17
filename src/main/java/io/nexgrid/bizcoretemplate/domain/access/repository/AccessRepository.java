package io.nexgrid.bizcoretemplate.domain.access.repository;

import io.nexgrid.bizcoretemplate.domain.access.Access;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.ReactiveQuerydslPredicateExecutor;

public interface AccessRepository extends MongoRepository<Access, String>, ReactiveQuerydslPredicateExecutor<Access> {

}
