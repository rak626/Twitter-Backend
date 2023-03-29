package com.rakesh.TwitterBackend.services;


import com.rakesh.TwitterBackend.payloads.requests.FollowDto;
import com.rakesh.TwitterBackend.payloads.requests.FollowRequest;
import com.rakesh.TwitterBackend.payloads.requests.UserDto;

import java.util.List;

public interface FollowService {

    FollowDto follow(FollowRequest followRequest);
    void unFollow(FollowRequest followRequest);


    // service for user details

    List<UserDto> findByFollower(String followerId);
    List<UserDto> findByFollowing(String followingId);

}
