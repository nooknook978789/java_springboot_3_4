package com.north.springboot3_4;

import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    // getAll
    public List<MemberModel> findAll(){
        return memberRepository.findAll();
    }

    // register
    public MemberModel createMember(MemberRequest request){
        MemberModel newMember = new MemberModel();
        newMember.setMobile(request.getMobile());
        newMember.setMobile2(request.getMobile2());
        newMember.setPassword(request.getPassword());
        newMember.setEmail(request.getEmail());
        newMember.setName(request.getName());
        return  memberRepository.save(newMember);
    }

    // find by mobile
    public MemberModel findByMobile(String mobile){
        return memberRepository.findByMobile(mobile);
    }

    // update by mobile
    public MemberModel updateByMobile(String mobile, MemberRequest request) throws BadRequestException {
        MemberModel memberUpdate = memberRepository.findByMobile(mobile);
        memberUpdate.setName(request.getName());
//        memberUpdate.setPassword(request.getPassword());
        memberUpdate.setEmail(request.getEmail());
        memberUpdate.setMobile2(request.getMobile2());
        return memberRepository.save(memberUpdate);
    }

    // delete by mobile
    @Transactional
    public void deleteByMobile(String mobile){
        memberRepository.deleteByMobile(mobile);
    }

    //delete all
    public void deleteAll(){
        memberRepository.deleteAll();
    }
}
