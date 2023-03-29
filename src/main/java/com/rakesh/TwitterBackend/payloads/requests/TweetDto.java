package com.rakesh.TwitterBackend.payloads.requests;

import lombok.*;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TweetDto {

    private String id;
    private String message;
    private String image;
    private UserDto user;
    private Long likes;
    private Long retweets;
    private Set<String> hashTags = new LinkedHashSet<>();
    private Date createdAt;
    private Date updatedAt;
}
