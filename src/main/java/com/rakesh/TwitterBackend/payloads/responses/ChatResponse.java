package com.rakesh.TwitterBackend.payloads.responses;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChatResponse {
    private HttpStatus status;
    private String response;
}
