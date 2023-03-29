package com.rakesh.TwitterBackend.payloads.requests;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DirectMessageRequestDto {

    private String content;

    private String senderId;
    private String receiverId;
}
