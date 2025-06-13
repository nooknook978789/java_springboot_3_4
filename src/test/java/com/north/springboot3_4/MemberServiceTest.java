package com.north.springboot3_4;

import org.apache.coyote.BadRequestException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;

import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MemberServiceTest {
    @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private MemberService memberService;

    @Test
    void getMemberByMobile_shouldHasMemberModel() {
        // input
        MemberRequest mockMemberRequest = new MemberRequest();
        MemberModel mockMemberModel = new MemberModel();
        mockMemberModel.setId(1L);
        // mocking
        when(memberRepository.save(any(MemberModel.class))).thenReturn(mockMemberModel);
        MemberModel result = memberService.createMember(mockMemberRequest);
        // assert
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getId()).isNotNull();
        Assertions.assertThat(result).isEqualTo(mockMemberModel);
        verify(memberRepository,times(1)).save(any(MemberModel.class));
    }

    @Test
    void updateMember_shouldUpdateMemberModel()  throws BadRequestException {
        MemberRequest mockMemberRequest = new MemberRequest();
        mockMemberRequest.setName("John Doe");
        mockMemberRequest.setEmail("john@example.com");
        mockMemberRequest.setMobile2("987654321");

        MemberModel mockMemberModel = new MemberModel();
        mockMemberModel.setMobile("123456789");

        // Mock findByMobile
        when(memberRepository.findByMobile(anyString())).thenReturn(mockMemberModel);
        // Mock save
        when(memberRepository.save(any(MemberModel.class))).thenReturn(mockMemberModel);

        MemberModel result = memberService.updateByMobile(mockMemberModel.getMobile(), mockMemberRequest);

        Assertions.assertThat(result).isNotNull();

    }


    //test delete
    @Test
    void deleteMember_shouldDeleteMemberModel() throws BadRequestException {
        // mock data
        String mobile = "123456789";
        MemberModel mockMemberModel = new MemberModel();
        mockMemberModel.setId(1L);
        mockMemberModel.setMobile(mobile);

        when(memberRepository.findByMobile(mobile)).thenReturn(mockMemberModel);
        memberService.deleteByMobile(mobile);
        verify(memberRepository, times(1)).delete(mockMemberModel);
    }


}
