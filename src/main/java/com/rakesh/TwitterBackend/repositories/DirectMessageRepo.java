package com.rakesh.TwitterBackend.repositories;

import com.rakesh.TwitterBackend.entities.DirectMessage;
import com.rakesh.TwitterBackend.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DirectMessageRepo extends JpaRepository<DirectMessage, Long> {

    List<DirectMessage> findBySender(User senderId);
    List<DirectMessage> findByReceiver(User receiverId);

}
