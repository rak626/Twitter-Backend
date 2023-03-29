package com.rakesh.TwitterBackend.payloads.responses;

import com.rakesh.TwitterBackend.entities.User;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DirectMessageResponseDto {

    private Long id;
    private String content;

    private User sender;

    private User receiver;

    private Date createdAt;
    private Date updatedAt;
}
