package com.mamoru.memberservice;

import com.mamoru.memberservice.repository.MemberRepository;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSFile;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * user: alekseyb
 * date: 05.07.18
 */
@Service
public class MemberService  {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    GridFsOperations gridFsOperations;

    public GridFSDBFile getMemberImageByMemberId(String id){
        Member m = memberRepository.findOne(id);
        return getImage(m.picture);
    }

    public GridFSDBFile getImage(Object imageid) {
        return gridFsOperations.findOne(new Query(Criteria.where("_id").is(imageid)));
    }

    public void delete(String id){
        memberRepository.delete(id);
    }

    public Member save(Member member, MultipartFile image) throws IOException {
        GridFSFile store = gridFsOperations.store(image.getInputStream(), image.getName(),image.getContentType());
        member.setPicture((ObjectId) store.getId());
        return memberRepository.save(member);
    }

    public Member updateImage(String memberId, MultipartFile image) throws IOException {
        Member one = memberRepository.findOne(memberId);
        GridFSFile store = gridFsOperations.store(image.getInputStream(), image.getName(), image.getContentType());
        ObjectId temp = one.getPicture();
        one.setPicture((ObjectId) store.getId());
        gridFsOperations.delete(new Query(Criteria.where("_id").is(temp)));
        return one;
    }

    public Member getMember(String id) {
        return memberRepository.findOne(id);
    }

    public Member findMember(String id) {
        return memberRepository.findOne(id);
    }

    public List<Member> findAll() {
        return memberRepository.findAll();
    }
}
