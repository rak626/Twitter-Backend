package com.rakesh.TwitterBackend.services;

import com.rakesh.TwitterBackend.payloads.requests.UserDto;
import com.rakesh.TwitterBackend.payloads.responses.UserResponseDto;

import java.util.List;

public interface UserService {

    UserDto createUser(UserDto user);
    void deleteUser(String id);
    UserDto updateUser(String id , UserDto user);
    UserDto getUserById(String id);
    List<UserDto> getAllUser();


    // user profiles
    UserResponseDto findUserProfiles(String userId);
}
