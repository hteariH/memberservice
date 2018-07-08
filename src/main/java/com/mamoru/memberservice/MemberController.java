package com.mamoru.memberservice;

import com.mamoru.memberservice.repository.MemberRepository;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSFile;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.stream.Collectors;

/**
 * user: alekseyb
 * date: 03.07.18
 */
@RestController
public class MemberController {


    @Autowired
    GridFsOperations gridFsOperations;

    @Autowired
    MemberService memberService;

    @RequestMapping(method = RequestMethod.GET, value = "/members", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Iterable<MemberDTO> members(){
        return memberService.findAll().stream()
                .map(m -> new MemberDTO(m.getId(),m.getFirstName(), m.getLastName(), m.getBirthDate(), m.getPostalCode(), m.getPicture().toString()))
                .collect(Collectors.toList());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/members/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public MemberDTO getMember(@PathVariable String id){
        Member m = memberService.findMember(id);
        return new MemberDTO(m.getId(),m.getFirstName(), m.getLastName(), m.getBirthDate(), m.getPostalCode(), m.getPicture().toString());
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/members/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public void deleteMember(@PathVariable String id){
        memberService.delete(id);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/members/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public String updateMember(@PathVariable String id, @RequestPart("member") MemberDTO member, @RequestPart("image") MultipartFile image) throws IOException {
        Member m = memberService.getMember(id);
        m.setFirstName(member.getFirstName());
        m.setLastName(member.getLastName());
        m.setBirthDate(member.getBirthDate());
        m.setPostalCode(member.getPostalCode());
        return memberService.save(m,image).getId();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/members/{id}/image")
    public ResponseEntity<InputStreamResource> memberImage(@PathVariable String id){
        GridFSDBFile image = memberService.getMemberImageByMemberId(id);
        return ResponseEntity
                .ok()
                .contentType(MediaType.valueOf(image.getContentType()))
                .body(new InputStreamResource(image.getInputStream()));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/members/{id}/image")
    public String memberUpdateImage(@PathVariable String id, @RequestBody MultipartFile image) throws IOException {
        return memberService.updateImage(id,image).getPicture().toString();
    }


    @RequestMapping(method = RequestMethod.GET, value = "/members/image/{imageid}")
    public ResponseEntity<InputStreamResource> getImage(@PathVariable String imageid) throws IOException {
        GridFSDBFile imageFile = memberService.getImage(imageid);
        return ResponseEntity
                .ok()
                .contentType(MediaType.valueOf(imageFile.getContentType()))
                .contentLength(imageFile.getLength())
                .body(new InputStreamResource(imageFile.getInputStream()));
    }

    @RequestMapping(method=RequestMethod.POST, value="/members")
    public String save(@RequestPart("member") Member memberDTO, @RequestPart("image")MultipartFile image) throws IOException {
        Member member = new Member(memberDTO.getFirstName(), memberDTO.getLastName(), memberDTO.getBirthDate(), memberDTO.getPostalCode());
        return memberService.save(member,image).getId();
    }


}
