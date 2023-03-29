package com.rakesh.TwitterBackend.services;

import com.rakesh.TwitterBackend.payloads.requests.ChatRequest;
import com.rakesh.TwitterBackend.payloads.responses.ChatResponse;

public interface ChatGPTService {
    ChatResponse getReply(ChatRequest chatRequest);
}
