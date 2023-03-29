package com.rakesh.TwitterBackend.services.impl;

import com.rakesh.TwitterBackend.entities.Tweet;
import com.rakesh.TwitterBackend.entities.User;
import com.rakesh.TwitterBackend.exceptions.ResourceNotFoundException;
import com.rakesh.TwitterBackend.payloads.requests.SearchRequest;
import com.rakesh.TwitterBackend.payloads.requests.TweetDto;
import com.rakesh.TwitterBackend.repositories.TweetRepo;
import com.rakesh.TwitterBackend.repositories.UserRepo;
import com.rakesh.TwitterBackend.services.TweetService;
import com.rakesh.TwitterBackend.utils.DtoConverters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TweetServiceImpl implements TweetService {

    @Autowired
    private TweetRepo tweetRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private DtoConverters dtoConverters;

    @Override
    public TweetDto createTweet(String userId, TweetDto tweetDto) {
        // find user
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "user id", userId));
        //setting values
        tweetDto.setId(UUID.randomUUID().toString());
        if(tweetDto.getImage() == null) tweetDto.setImage("default.png");
        tweetDto.setUser(dtoConverters.userToUserDto(user));
        tweetDto.setLikes(0L);
        tweetDto.setRetweets(0L);
        tweetDto.setCreatedAt(new Date());
        tweetDto.setUpdatedAt(tweetDto.getCreatedAt());

        //saving in database
        Tweet save = this.tweetRepo.save(dtoConverters.tweetDtoTotweet(tweetDto));
        return dtoConverters.tweetToTweetDto(save);
    }

    @Override
    public TweetDto getTweet(String id) {
        Tweet tweet = tweetRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tweet", "id", id));
        return dtoConverters.tweetToTweetDto(tweet);
    }

    @Override
    public TweetDto updateTweet(String postId, TweetDto tweetDto) {
        Tweet tweet = tweetRepo.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Tweet", "id", postId));
        tweet.setMessage(tweetDto.getMessage());
        tweet.setUpdatedAt(new Date());
        tweet = tweetRepo.save(tweet);
        return dtoConverters.tweetToTweetDto(tweet);
    }

    @Override
    public void deleteTweet(String id) {
        tweetRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tweet", "id", id));
        tweetRepo.deleteById(id);
    }

    @Override
    public List<TweetDto> getAllTweets() {
        List<Tweet> allTweets = tweetRepo.findAll();
        List<TweetDto> tweetDtos = allTweets.stream()
                .map(tweet -> dtoConverters.tweetToTweetDto(tweet))
                .collect(Collectors.toList());
        return  tweetDtos;
    }

    @Override
    public List<TweetDto> getAllTweetsByUser(String userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "user id", userId));
        List<Tweet> allTweetsByUser = tweetRepo.findByUser(user);
        List<TweetDto> collect = allTweetsByUser.stream()
                .map(tweet -> dtoConverters.tweetToTweetDto(tweet))
                .collect(Collectors.toList());
        return collect;
    }

    @Override
    public TweetDto likeTweet(String tweetId) {
        Tweet tweet = tweetRepo.findById(tweetId)
                .orElseThrow(() -> new ResourceNotFoundException("Tweet", "id", tweetId));
        tweet.setLikes(tweet.getLikes() + 1);
        Tweet savedTweet = tweetRepo.save(tweet);
        return dtoConverters.tweetToTweetDto(savedTweet);
    }

    @Override
    public TweetDto retweetTweet(String tweetId) {
        Tweet tweet = tweetRepo.findById(tweetId)
                .orElseThrow(() -> new ResourceNotFoundException("Tweet", "id", tweetId));
        tweet.setRetweets(tweet.getRetweets() + 1);
        Tweet savedTweet = tweetRepo.save(tweet);
        return dtoConverters.tweetToTweetDto(savedTweet);
    }

    @Override
    public TweetDto dislikeTweet(String tweetId) {
        Tweet tweet = tweetRepo.findById(tweetId)
                .orElseThrow(() -> new ResourceNotFoundException("Tweet", "id", tweetId));
        Tweet dislikedTweet = new Tweet();
        if(tweet.getLikes() > 0){
            tweet.setLikes(tweet.getLikes() - 1);
            dislikedTweet = tweetRepo.save(tweet);
        } else {
            dislikedTweet = tweet;
        }
        return dtoConverters.tweetToTweetDto(dislikedTweet);
    }

    @Override
    public List<TweetDto> getAllTweetsByHashTags(SearchRequest searchRequest) {
        List<Tweet> allTweets = tweetRepo.findByHashTagsIn(searchRequest.getHashtags());
        List<TweetDto> tweetDtos = allTweets.stream()
                .map(tweet -> dtoConverters.tweetToTweetDto(tweet))
                .collect(Collectors.toList());
        return tweetDtos;
    }

    @Override
    public List<TweetDto> getAllTweetsByKeywords(SearchRequest searchRequest) {
        List<Tweet> allTweets = tweetRepo.findByMessageContaining(searchRequest.getKeyword());
        List<TweetDto> tweetDtos = allTweets.stream()
                .map(tweet -> dtoConverters.tweetToTweetDto(tweet))
                .collect(Collectors.toList());
        return tweetDtos;
    }

}
