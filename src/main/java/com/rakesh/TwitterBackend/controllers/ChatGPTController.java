package com.rakesh.TwitterBackend.controllers;

import com.rakesh.TwitterBackend.payloads.requests.ChatRequest;
import com.rakesh.TwitterBackend.payloads.responses.ChatResponse;
import com.rakesh.TwitterBackend.services.ChatGPTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/chat")
public class ChatGPTController {

    @Autowired
    private ChatGPTService chatGPTService;

    @PostMapping
    public ChatResponse getQueryResponse(@RequestBody ChatRequest chatRequest) {
        return chatGPTService.getReply(chatRequest);
    }

}
