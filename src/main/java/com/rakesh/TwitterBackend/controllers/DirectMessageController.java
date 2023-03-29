package com.rakesh.TwitterBackend.controllers;

import com.rakesh.TwitterBackend.payloads.requests.DirectMessageRequestDto;
import com.rakesh.TwitterBackend.payloads.responses.ApiResponse;
import com.rakesh.TwitterBackend.payloads.responses.DirectMessageResponseDto;
import com.rakesh.TwitterBackend.services.DirectMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class DirectMessageController {

    @Autowired
    private DirectMessageService directMessageService;

    @PostMapping("/create")
    public ResponseEntity<DirectMessageResponseDto> sendMessage(@RequestBody DirectMessageRequestDto directMessageRequestDto){
        DirectMessageResponseDto directMessage = directMessageService.createDirectMessage(directMessageRequestDto);
        return new ResponseEntity<>(directMessage, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{messageId}")
    public ApiResponse deleteMessage(@PathVariable Long messageId){
        directMessageService.deleteDirectMessage(messageId);
        return new ApiResponse("Message Deleted Successfully", true, HttpStatus.ACCEPTED);
    }

    @GetMapping("/sender/{senderId}")
    public List<DirectMessageResponseDto> getAllMessageBySender(@PathVariable String senderId){
        List<DirectMessageResponseDto> messages = directMessageService.getAllDirectMessageBySender(senderId);
        return messages;
    }

    @GetMapping("/receiver/{receiverId}")
    public List<DirectMessageResponseDto> getAllMessageByReceiver(@PathVariable String receiverId){
        List<DirectMessageResponseDto> messages = directMessageService.getAllDirectMessageBySender(receiverId);
        return messages;
    }
}
