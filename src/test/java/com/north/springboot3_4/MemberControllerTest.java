package com.north.springboot3_4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberService memberService;


    private MemberModel mockupModel;

    //input
    @BeforeEach
    void setup() {
        memberRepository.deleteAll();
        mockupModel = MemberModel.builder()
                .name("name1")
                .email("email1")
                .password("password1")
                .mobile("099999999")
                .mobile2("098999999")
                .build();
        memberRepository.save(mockupModel);
    }

    // create
    @Test
    void addMember_shouldCreateNewMemberModel() throws Exception {
        // mock
        String req = """
                {
                    "name":"testCreateAddMember",
                    "email":"testCreateAddMember@email.com",
                    "mobile":"0999999999",
                    "mobile2":"0989999999"
                }
                """;
        // expect
        mockMvc.perform(post("/api/member")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(req))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("testCreateAddMember"))
                .andExpect(jsonPath("$.email").value("testCreateAddMember@email.com"))
                .andExpect(jsonPath("$.mobile").value("0999999999"));
    }

    // test update
    @Test
    void updateMember_shouldUpdateMemberModel() throws Exception {
//        memberRepository.deleteAll();
//        MemberModel testUpdateModel = MemberModel.builder()
//                .name("name1")
//                .email("email1")
//                .password("password1")
//                .mobile("098999999")
//                .mobile2("098999999")
//                .build();
//        memberRepository.save(testUpdateModel);

        String req = """
                {
                    "name":"testUpdateMember",
                    "email":"testUpdateMember@email.com",
                    "mobile2":"0899999999"
                }
        """;
        // expect
        mockMvc.perform(post("/api/member/{mobile}", mockupModel.getMobile())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(req))
                .andExpect(status().is(205))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("testUpdateMember"))
                .andExpect(jsonPath("$.email").value("testUpdateMember@email.com"))
                .andExpect(jsonPath("$.mobile2").value("0899999999"));
    }

    // test GetMapping
    @Test
    void getMemeberByMobile_shouldHasMemberModel() throws Exception {
        mockMvc.perform(get("/api/member/{mobile}", mockupModel.getMobile()))
                .andExpect(status().is(206))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("name1"))
                .andExpect(jsonPath("$.email").value("email1"))
                .andExpect(jsonPath("$.mobile").value("099999999"))
                .andExpect(jsonPath("$.mobile2").value("098999999"));
    }

    //test Delete
    @Test
    void deleteMemberByMobile_shouldReturnSuccessMessage() throws Exception {
        String mobile = "099999999";

        mockMvc.perform(delete("/api/member/{mobile}", mobile))
                .andExpect(status().is(200))
                .andExpect(content().string("Delete Success"));

    }

}
