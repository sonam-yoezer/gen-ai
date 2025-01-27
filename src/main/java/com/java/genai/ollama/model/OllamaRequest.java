package com.java.genai.ollama.model;

import lombok.Data;

@Data
public class OllamaRequest {
    private String model; // e.g., "llama3.1:8b"
    private String prompt;
}
