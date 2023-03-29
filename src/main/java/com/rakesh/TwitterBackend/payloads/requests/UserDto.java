package com.rakesh.TwitterBackend.payloads.requests;


import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private String id;
    private String email;
    private String password;
    private String username;
    private String firstName;
    private String lastName;
    private String avatarUrl;
    private Date createdAt;
    private Date updatedAt;
}
