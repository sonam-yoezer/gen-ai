package com.java.genai.ollama.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true) // Ignore unknown fields like "model"
public class OllamaResponse {
    @JsonProperty("response") // Map the JSON "response" field
    private String response;

    // Getter and Setter for 'response'
    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
