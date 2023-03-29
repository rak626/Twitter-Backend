package com.rakesh.TwitterBackend.services.impl;

import com.rakesh.TwitterBackend.payloads.requests.ChatRequest;
import com.rakesh.TwitterBackend.payloads.responses.ChatResponse;
import com.rakesh.TwitterBackend.services.ChatGPTService;
import com.rakesh.TwitterBackend.utils.AppConstants;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Optional;



@Service
public class ChatGPTServiceImpl implements ChatGPTService {




    public ChatResponse getReply(ChatRequest chatRequest) {

        try {

            String reply = generateResponse(chatRequest.getQuery());
            return ChatResponse.builder()
                    .response(reply)
                    .status(HttpStatus.ACCEPTED)
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ChatResponse.builder()
                .response(AppConstants.MESSAGE_ON_FAILED_QUERY)
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    private static String generateResponse(String prompt) throws Exception {
        HttpURLConnection con = (HttpURLConnection) new URL(AppConstants.OPENAI_API_URL).openConnection();

        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Authorization", "Bearer " + AppConstants.OPENAI_API_KEY);

        JSONObject data = new JSONObject();
        data.put("model", "text-davinci-003");
        data.put("prompt", prompt);
        data.put("max_tokens", AppConstants.MAX_TOKENS);
        data.put("temperature", 1.0);

        con.setDoOutput(true);
        con.getOutputStream().write(data.toString().getBytes());

        Optional<String> optionalOutput = new BufferedReader(new InputStreamReader(con.getInputStream())).lines()
                .reduce((a, b) -> a + b);
        String output = optionalOutput.orElse("Unable to complete");

        return new JSONObject(output).getJSONArray("choices").getJSONObject(0).getString("text").replace("\n", "");
    }
}

