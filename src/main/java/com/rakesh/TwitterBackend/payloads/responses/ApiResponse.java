package com.rakesh.TwitterBackend.payloads.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {

    private String message;
    private boolean status;
    private HttpStatus statusCode;
}
