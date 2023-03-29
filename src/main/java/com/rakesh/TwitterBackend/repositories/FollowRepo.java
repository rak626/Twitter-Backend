package com.rakesh.TwitterBackend.repositories;


import com.rakesh.TwitterBackend.entities.Follow;
import com.rakesh.TwitterBackend.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FollowRepo extends JpaRepository<Follow, Integer> {

    Follow findByFollowerAndFollowing(User follower, User following);

    //use for user service
    List<Follow> findByFollower(User follower);
    List<Follow> findByFollowing(User following);

}
