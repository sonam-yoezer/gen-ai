package com.java.genai.ollama.service;

import com.java.genai.ollama.model.OllamaRequest;
import com.java.genai.ollama.model.OllamaResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class OllamaService {

    private final RestTemplate restTemplate = new RestTemplate();
    private static final String OLLAMA_API_URL = "http://localhost:11434/api/generate";

    public String generateResponse(String prompt) {
        try {
            // Prepare the request payload
            OllamaRequest request = new OllamaRequest();
            request.setModel("llama3.1:8b"); // Specify the model
            request.setPrompt(prompt);

            // Set headers
            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "application/json");

            // Create the HTTP entity
            HttpEntity<OllamaRequest> entity = new HttpEntity<>(request, headers);

            // Send the request to Ollama API
            ResponseEntity<String> response = restTemplate.exchange(
                    OLLAMA_API_URL,
                    HttpMethod.POST,
                    entity,
                    String.class // Expect the response as a raw string
            );

            // Parse the NDJSON response
            String responseBody = response.getBody();
            if (responseBody != null) {
                // Split the response by newlines and process each line
                String[] lines = responseBody.split("\n");
                StringBuilder fullResponse = new StringBuilder();

                ObjectMapper objectMapper = new ObjectMapper();
                for (String line : lines) {
                    // Parse each line as a JSON object
                    OllamaResponse ollamaResponse = objectMapper.readValue(line, OllamaResponse.class);
                    fullResponse.append(ollamaResponse.getResponse());
                }

                return fullResponse.toString();
            } else {
                return "No response from Ollama";
            }
        } catch (Exception e) {
            return "An error occurred: " + e.getMessage();
        }
    }
}
