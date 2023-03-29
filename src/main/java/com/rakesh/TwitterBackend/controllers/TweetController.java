package com.rakesh.TwitterBackend.controllers;

import com.rakesh.TwitterBackend.payloads.requests.SearchRequest;
import com.rakesh.TwitterBackend.payloads.requests.TweetDto;
import com.rakesh.TwitterBackend.payloads.responses.ApiResponse;
import com.rakesh.TwitterBackend.services.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tweets")
public class TweetController {

    @Autowired
    private TweetService tweetService;

    @PostMapping("/create/{userId}")
    public ResponseEntity<TweetDto> createTweet(@PathVariable("userId") String id, @RequestBody TweetDto tweetDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(tweetService.createTweet(id, tweetDto));
    }

    @PostMapping("/like/{tweetId}")
    public TweetDto likeTweet(@PathVariable String tweetId){
        return tweetService.likeTweet(tweetId);
    }

    @PostMapping("/dislike/{tweetId}")
    public TweetDto dislikeTweet(@PathVariable String tweetId){
        return tweetService.dislikeTweet(tweetId);
    }

    @PostMapping("/retweet/{tweetId}")
    public TweetDto retweetTweet(@PathVariable String tweetId){
        return tweetService.retweetTweet(tweetId);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<TweetDto> updateTweet(@PathVariable String id, @RequestBody TweetDto tweetDto) {
        return ResponseEntity.ok(tweetService.updateTweet(id, tweetDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteTweet(@PathVariable("id") String id) {
        tweetService.deleteTweet(id);
        return ResponseEntity.ok(new ApiResponse("Successfully Deleted : " + id, true, HttpStatus.OK));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TweetDto> getTweet(@PathVariable("id") String id) {
        return ResponseEntity.ok(tweetService.getTweet(id));
    }

    @GetMapping
    public ResponseEntity<List<TweetDto>> getAllTweets(){
        List<TweetDto> allTweets = tweetService.getAllTweets();
        return ResponseEntity.ok(allTweets);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<TweetDto>> findAllTweetsByUser(@PathVariable String userId){
        List<TweetDto> allTweetsByUser = tweetService.getAllTweetsByUser(userId);
        return ResponseEntity.ok(allTweetsByUser);
    }

    @GetMapping("/search/hashTags")
    public List<TweetDto> findAllTweetByHashTags(@RequestBody SearchRequest searchRequest){
        return tweetService.getAllTweetsByHashTags(searchRequest);
    }

    @GetMapping("/search/keyword")
    public List<TweetDto> findAllTweetByKeyword(@RequestBody SearchRequest searchRequest){
        return tweetService.getAllTweetsByKeywords(searchRequest);
    }


}
