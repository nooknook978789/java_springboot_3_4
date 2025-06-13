package com.north.springboot3_4;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/member")
public class MemberController {
    @Autowired
    private MemberService memberService;

    // todo: create API Register Member
    // todo: 1. API name : registerMember
    // todo: Endpoint : "/api/member/"
    // todo: Method : Post
    // todo: Header :
    // todo: Body : {username: , password: , email: , mobile:  }
    // todo: get member by mobile
    @PostMapping
    public ResponseEntity<MemberModel> addMember(@RequestBody MemberRequest memberRequest){
        MemberModel data = memberService.createMember(memberRequest);
        return ResponseEntity.ok().body(data);
    }

    // todo: 2. API name : getMemberByMobile
    // todo: Endpoint : "/api/member/{mobile}"
    // todo: Method : Get
    // todo: Header :
    // todo: Body : { }
    // todo: update member by mobile
    @GetMapping("/{mobile}")
    public ResponseEntity<MemberModel> getMemberByMobile(@PathVariable String mobile){
        MemberModel data = memberService.findByMobile(mobile);
        return ResponseEntity.status(206).body(data);
    }

    // todo: 3. API name : updateMemberByMobile
    // todo: Endpoint : "/api/member/{mobile}"
    // todo: Method : Post
    // todo: Header :
    // todo: Body :  { name:  , email: }
    @PostMapping("/{mobile}")
    public ResponseEntity<MemberModel> updateMember(@PathVariable String mobile ,@RequestBody MemberRequest memberRequest) throws BadRequestException {
        MemberModel data = memberService.updateByMobile(mobile,memberRequest);
        return ResponseEntity.status(205).body(data);
    }
}
