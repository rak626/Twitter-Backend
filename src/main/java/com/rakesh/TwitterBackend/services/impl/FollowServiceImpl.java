package com.rakesh.TwitterBackend.services.impl;

import com.rakesh.TwitterBackend.entities.Follow;
import com.rakesh.TwitterBackend.entities.User;
import com.rakesh.TwitterBackend.exceptions.ResourceNotFoundException;
import com.rakesh.TwitterBackend.payloads.requests.FollowDto;
import com.rakesh.TwitterBackend.payloads.requests.FollowRequest;
import com.rakesh.TwitterBackend.payloads.requests.UserDto;
import com.rakesh.TwitterBackend.repositories.FollowRepo;
import com.rakesh.TwitterBackend.repositories.UserRepo;
import com.rakesh.TwitterBackend.services.FollowService;
import com.rakesh.TwitterBackend.utils.DtoConverters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FollowServiceImpl implements FollowService {
    @Autowired
    private FollowRepo followRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private DtoConverters dtoConverters;

    @Override
    public FollowDto follow(FollowRequest followRequest) {
        User follower = userRepo.findById(followRequest.getFollowerId())
                .orElseThrow(() -> new ResourceNotFoundException("User" , "follower_id" , followRequest.getFollowerId()));
        User following = userRepo.findById(followRequest.getFollowingId())
                .orElseThrow(() -> new ResourceNotFoundException("User" , "following_id" , followRequest.getFollowingId()));
        Follow follow = new Follow();
        follow.setFollower(follower);
        follow.setFollowing(following);
        follow = followRepo.save(follow);
        return dtoConverters.followToFollowDto(follow);
    }

    @Override
    public void unFollow(FollowRequest followRequest) {
        User follower = userRepo.findById(followRequest.getFollowerId())
                .orElseThrow(() -> new ResourceNotFoundException("User" , "follower_id" , followRequest.getFollowerId()));
        User following = userRepo.findById(followRequest.getFollowingId())
                .orElseThrow(() -> new ResourceNotFoundException("User" , "following_id" , followRequest.getFollowingId()));
        Follow follow = followRepo.findByFollowerAndFollowing(follower, following);
        followRepo.delete(follow);
    }

    @Override
    public List<UserDto> findByFollower(String followerId) {
        User follower = userRepo.findById(followerId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "user id", followerId));
        List<Follow> followers = followRepo.findByFollower(follower);
        List<User> users = new ArrayList<>();
        for(Follow f : followers){
            users.add(f.getFollowing());
        }
        List<UserDto> userDtoList = users.stream().map(user -> dtoConverters.userToUserDto(user)).collect(Collectors.toList());
        return userDtoList;
    }

    @Override
    public List<UserDto> findByFollowing(String followingId) {
        return null;
    }
}
