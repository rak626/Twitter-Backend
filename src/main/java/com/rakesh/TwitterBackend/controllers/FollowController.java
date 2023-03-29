package com.rakesh.TwitterBackend.controllers;

import com.rakesh.TwitterBackend.payloads.requests.FollowDto;
import com.rakesh.TwitterBackend.payloads.requests.FollowRequest;
import com.rakesh.TwitterBackend.payloads.requests.UserDto;
import com.rakesh.TwitterBackend.payloads.responses.ApiResponse;
import com.rakesh.TwitterBackend.services.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class FollowController {

    @Autowired
    private FollowService followService;


    @PostMapping("/follow")
    public FollowDto follow(@RequestBody FollowRequest followRequest){
        return this.followService.follow(followRequest);
    }

    @PostMapping("/unfollow")
    public ApiResponse unfollow(@RequestBody FollowRequest followRequest){
        this.followService.unFollow(followRequest);
        return new ApiResponse("User unfollowed successfully" , true , HttpStatus.OK);
    }

    @GetMapping("/{followerId}")
    public List<UserDto> findByFollower(@PathVariable String followerId){
        List<UserDto> followers = followService.findByFollower(followerId);
        return followers;
    }
}
