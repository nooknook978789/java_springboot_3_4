package com.north.springboot3_4;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MemberRequest {
    private String name;
    private String email;
    private String password;
    private String mobile;
    private String mobile2;

}
