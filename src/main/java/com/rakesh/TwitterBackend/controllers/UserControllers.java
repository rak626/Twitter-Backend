package com.rakesh.TwitterBackend.controllers;

import com.rakesh.TwitterBackend.payloads.responses.ApiResponse;
import com.rakesh.TwitterBackend.payloads.requests.UserDto;
import com.rakesh.TwitterBackend.payloads.responses.UserResponseDto;
import com.rakesh.TwitterBackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserControllers {

    @Autowired
    private UserService userService;


    @PostMapping
    public UserDto createdUser(@RequestBody UserDto userDto){
        UserDto createdUser = userService.createUser(userDto);
        return createdUser;
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse deletedUser(@PathVariable String id){
        userService.deleteUser(id);
        return new ApiResponse("Deleted Successfully", true, HttpStatus.ACCEPTED);
    }

    @PutMapping("/update/{id}")
    public UserDto updateUser(@PathVariable String id, @RequestBody UserDto user){
        return userService.updateUser(id, user);
    }

    @GetMapping
    public List<UserDto> getAllUsers(){
        return  userService.getAllUser();
    }

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable String id){
        return userService.getUserById(id);
    }

    @GetMapping("/profile/{userId}")
    public UserResponseDto getUserProfileByUserId(@PathVariable String userId){
        UserResponseDto userProfile = userService.findUserProfiles(userId);
        return userProfile;
    }
}
