package com.rakesh.TwitterBackend.payloads.requests;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FollowDto {
    
    private UserDto follower;
    private UserDto following;

}
