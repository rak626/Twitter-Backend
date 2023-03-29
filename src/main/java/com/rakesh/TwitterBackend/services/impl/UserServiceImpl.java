package com.rakesh.TwitterBackend.services.impl;


import com.rakesh.TwitterBackend.entities.User;
import com.rakesh.TwitterBackend.exceptions.ResourceNotFoundException;
import com.rakesh.TwitterBackend.payloads.requests.TweetDto;
import com.rakesh.TwitterBackend.payloads.requests.UserDto;
import com.rakesh.TwitterBackend.payloads.responses.UserResponseDto;
import com.rakesh.TwitterBackend.repositories.FollowRepo;
import com.rakesh.TwitterBackend.repositories.TweetRepo;
import com.rakesh.TwitterBackend.repositories.UserRepo;
import com.rakesh.TwitterBackend.services.TweetService;
import com.rakesh.TwitterBackend.services.UserService;
import com.rakesh.TwitterBackend.utils.AppConstants;
import com.rakesh.TwitterBackend.utils.DtoConverters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private DtoConverters dtoConverters;

    @Autowired
    private TweetRepo tweetRepo;

    @Autowired
    private FollowRepo followRepo;

    @Autowired
    private TweetService tweetService;

    @Override
    public UserDto createUser(UserDto userDto) {
        String randomId = UUID.randomUUID().toString();
        userDto.setId(randomId);
        userDto.setCreatedAt(new Date());
        userDto.setUpdatedAt(userDto.getCreatedAt());
        if(userDto.getAvatarUrl() == null)
            userDto.setAvatarUrl(AppConstants.DEFAULT_IMAGE);
        User savedUser = this.userRepo.save(dtoConverters.userDtoToUser(userDto));
        return dtoConverters.userToUserDto(savedUser);
    }

    @Override
    public void deleteUser(String id) {
        this.userRepo.deleteById(id);
    }

    @Override
    public UserDto updateUser(String id, UserDto userDto) {
        User optUser = this.userRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "userId", id));

        optUser.setUsername(userDto.getUsername());
        optUser.setFirstName(userDto.getFirstName());
        optUser.setEmail(userDto.getEmail());
        optUser.setPassword(userDto.getPassword());
        optUser.setLastName(userDto.getLastName());
        if(userDto.getAvatarUrl() == null)
            optUser.setAvatarUrl(userDto.getAvatarUrl());
        optUser.setUpdatedAt(new Date());
        User savedUser = userRepo.save(optUser);
        return dtoConverters.userToUserDto(savedUser);
    }

    @Override
    public UserDto getUserById(String id) {
        User optUser = userRepo.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("User", "userId", id));
        return dtoConverters.userToUserDto(optUser);
    }

    @Override
    public List<UserDto> getAllUser() {
        List<User> allFind = userRepo.findAll();
        return allFind.stream()
                .map(user -> dtoConverters.userToUserDto(user))
                .collect(Collectors.toList());
    }

    @Override
    public UserResponseDto findUserProfiles(String userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "user id", userId));
        List<TweetDto> foundTweets = tweetService.getAllTweetsByUser(userId);

        return UserResponseDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .password(user.getPassword())
                .username(user.getUsername())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .avatarUrl(user.getAvatarUrl())
                .tweets(foundTweets)
                .followers(followRepo.findByFollower(user))
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }
}
