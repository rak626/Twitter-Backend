package com.rakesh.TwitterBackend.repositories;

import com.rakesh.TwitterBackend.entities.Tweet;
import com.rakesh.TwitterBackend.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface TweetRepo extends JpaRepository<Tweet, String> {

    List<Tweet> findByUser(User user);
    List<Tweet> findByHashTagsIn(Set<String> hashtags);
    List<Tweet> findByMessageContaining(String keyword);
}
