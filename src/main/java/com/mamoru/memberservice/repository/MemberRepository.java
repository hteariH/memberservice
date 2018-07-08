package com.mamoru.memberservice.repository;

import com.mamoru.memberservice.Member;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * user: alekseyb
 * date: 03.07.18
 */
public interface MemberRepository extends MongoRepository<Member,String> {

    @Override
    Member findOne(String s);
    //    Member findFirstById(String id);

    @Override
    void delete(Member deleted);
}
