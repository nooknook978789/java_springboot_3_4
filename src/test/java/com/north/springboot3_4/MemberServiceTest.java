package com.north.springboot3_4;

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

    // input

    // mocking

    // assert
    @Test
    void getMemberByMobile_shouldHasMemberModel() throws Exception {
        MemberRequest mockMemberRequest = new MemberRequest();
        MemberModel mockMemberModel = new MemberModel();
        mockMemberModel.setId(1L);

        when(memberRepository.save(any(MemberModel.class))).thenReturn(mockMemberModel);

        MemberModel result = memberService.createMember(mockMemberRequest);
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getId()).isNotNull();
        Assertions.assertThat(result).isEqualTo(mockMemberModel);

        verify(memberRepository,times(1)).save(any(MemberModel.class));
    }

}
