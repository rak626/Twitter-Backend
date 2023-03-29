package com.rakesh.TwitterBackend.services;

import com.rakesh.TwitterBackend.payloads.requests.SearchRequest;
import com.rakesh.TwitterBackend.payloads.requests.TweetDto;

import java.util.List;

public interface TweetService {

    TweetDto createTweet(String userId, TweetDto tweet);
    TweetDto getTweet(String id);
    TweetDto updateTweet(String id, TweetDto tweet);
    void deleteTweet(String id);

    List<TweetDto> getAllTweets();

    ///

    List<TweetDto> getAllTweetsByUser(String userId);

    // like and retweet
    TweetDto likeTweet(String tweetId);
    TweetDto retweetTweet(String tweetId);
    TweetDto dislikeTweet(String tweetId);

    //search feature
    List<TweetDto> getAllTweetsByHashTags(SearchRequest searchRequest);
    List<TweetDto> getAllTweetsByKeywords(SearchRequest searchRequest);
}
