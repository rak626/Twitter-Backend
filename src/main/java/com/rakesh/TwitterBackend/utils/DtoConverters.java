package com.rakesh.TwitterBackend.utils;

import com.rakesh.TwitterBackend.entities.DirectMessage;
import com.rakesh.TwitterBackend.entities.Follow;
import com.rakesh.TwitterBackend.entities.Tweet;
import com.rakesh.TwitterBackend.entities.User;
import com.rakesh.TwitterBackend.payloads.requests.FollowDto;
import com.rakesh.TwitterBackend.payloads.requests.TweetDto;
import com.rakesh.TwitterBackend.payloads.requests.UserDto;
import com.rakesh.TwitterBackend.payloads.responses.DirectMessageResponseDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DtoConverters {

    @Autowired
    private ModelMapper modelMapper;

    public TweetDto tweetToTweetDto(Tweet tweet){
        TweetDto tweetDto = TweetDto.builder()
                .id(tweet.getId())
                .message(tweet.getMessage())
                .image(tweet.getImage())
                .likes(tweet.getLikes())
                .retweets(tweet.getRetweets())
                .createdAt(tweet.getCreatedAt())
                .updatedAt(tweet.getUpdatedAt())
                .user(this.userToUserDto(tweet.getUser()))
                .hashTags(tweet.getHashTags())
                .build();
        return tweetDto;
    }

    public Tweet tweetDtoTotweet(TweetDto tweetDto){
        Tweet tweet = Tweet.builder()
                .id(tweetDto.getId())
                .message(tweetDto.getMessage())
                .image(tweetDto.getImage())
                .likes(tweetDto.getLikes())
                .retweets(tweetDto.getRetweets())
                .createdAt(tweetDto.getCreatedAt())
                .updatedAt(tweetDto.getUpdatedAt())
                .user(this.userDtoToUser(tweetDto.getUser()))
                .hashTags(tweetDto.getHashTags())
                .build();
        return tweet;
    }

    public UserDto userToUserDto(User user){
        UserDto userDto = UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .password(user.getPassword())
                .username(user.getUsername())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .avatarUrl(user.getAvatarUrl())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
        return userDto;
    }

    public User userDtoToUser(UserDto userDto){
        User user = User.builder()
                .id(userDto.getId())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .username(userDto.getUsername())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .avatarUrl(userDto.getAvatarUrl())
                .createdAt(userDto.getCreatedAt())
                .updatedAt(userDto.getUpdatedAt())
                .build();
        return user;
    }

    public FollowDto followToFollowDto(Follow follow) {
        FollowDto followDto = FollowDto.builder()
                .following(this.userToUserDto(follow.getFollowing()))
                .follower(this.userToUserDto(follow.getFollower()))
                .build();
        return followDto;
    }


    public DirectMessageResponseDto directMessageToDirectMessageResponseDto(DirectMessage directMessage){
        DirectMessageResponseDto directMessageResponseDto = DirectMessageResponseDto.builder()
                .id(directMessage.getId())
                .content(directMessage.getContent())
                .sender(directMessage.getSender())
                .receiver(directMessage.getReceiver())
                .createdAt(directMessage.getCreatedAt())
                .updatedAt(directMessage.getUpdatedAt())
                .build();
        return directMessageResponseDto;
    }
}
