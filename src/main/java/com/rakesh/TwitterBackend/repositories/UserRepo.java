package com.rakesh.TwitterBackend.repositories;

import com.rakesh.TwitterBackend.entities.Follow;
import com.rakesh.TwitterBackend.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepo extends JpaRepository<User,String> {

    List<Follow> findFollowersById(Long id);
    List<Follow> findFollowingById(Long id);
}
