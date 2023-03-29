package com.rakesh.TwitterBackend.payloads.requests;

import lombok.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SearchRequest {

    private Set<String> hashtags = new LinkedHashSet<>();
    private String keyword;
}
