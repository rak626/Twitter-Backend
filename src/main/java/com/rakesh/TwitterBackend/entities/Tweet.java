package com.rakesh.TwitterBackend.entities;

import lombok.*;

import javax.persistence.*;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tweets")
public class Tweet {
    @Id
    private String id;
    private String message;
    private String image;
    private Long likes;
    private Long retweets;
    @ManyToOne
    private User user;
    private Date createdAt;
    private Date updatedAt;

    @ElementCollection(fetch = FetchType.EAGER)
    @Column(name = "hash_tag")
    @CollectionTable(name = "tweets_hash_tags", joinColumns = @JoinColumn(name = "tweet_id"))
    private Set<String> hashTags = new LinkedHashSet<>();

}
