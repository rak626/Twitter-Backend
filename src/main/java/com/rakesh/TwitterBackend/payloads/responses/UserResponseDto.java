package com.rakesh.TwitterBackend.payloads.responses;

import com.rakesh.TwitterBackend.entities.Follow;
import com.rakesh.TwitterBackend.payloads.requests.TweetDto;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {

    private String id;
    private String email;
    private String password;
    private String username;
    private String firstName;
    private String lastName;
    private String avatarUrl;
    private List<TweetDto> tweets = new ArrayList<>();
    private List<Follow> followers = new ArrayList<>();
    private List<Follow> followings = new ArrayList<>();
    private Date createdAt;
    private Date updatedAt;

}
