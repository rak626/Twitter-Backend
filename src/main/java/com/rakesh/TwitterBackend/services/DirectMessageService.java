package com.rakesh.TwitterBackend.services;

import com.rakesh.TwitterBackend.payloads.requests.DirectMessageRequestDto;
import com.rakesh.TwitterBackend.payloads.responses.DirectMessageResponseDto;

import java.util.List;

public interface DirectMessageService {

    //create
    DirectMessageResponseDto createDirectMessage(DirectMessageRequestDto directMessageRequestDto);

    //update
    DirectMessageResponseDto updateDirectMessage(DirectMessageRequestDto directMessageRequestDto);

    //delete
    void deleteDirectMessage(Long directMessageId);

    //get all direct Message by sender and receiver
    List<DirectMessageResponseDto> getAllDirectMessageByReceiver(String receiverId);


    //get all direct Message by sender
    List<DirectMessageResponseDto> getAllDirectMessageBySender(String senderId);
}
