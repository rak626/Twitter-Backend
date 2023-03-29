package com.rakesh.TwitterBackend.payloads.requests;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FollowRequest {

    private String followerId;
    private String followingId;
}
