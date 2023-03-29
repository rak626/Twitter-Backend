package com.rakesh.TwitterBackend.payloads.requests;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChatRequest {
    private String query;
}
