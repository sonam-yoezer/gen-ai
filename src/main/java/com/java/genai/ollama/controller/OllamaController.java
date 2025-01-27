package com.java.genai.ollama.controller;

import com.java.genai.ollama.service.OllamaService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/ollama")
public class OllamaController {

    private final OllamaService ollamaService;

    public OllamaController(OllamaService ollamaService) {
        this.ollamaService = ollamaService;
    }

    @PostMapping("/generate")
    public String generateResponse(@RequestParam String prompt) {
        return ollamaService.generateResponse(prompt);
    }
}