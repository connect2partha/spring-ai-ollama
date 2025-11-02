package com.codelounge.agent.ai.controller;

import com.codelounge.agent.ai.dto.PromptRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.*;

@RestController
public class AIController {
    private static final Logger LOG = LoggerFactory.getLogger(AIController.class);
    private final ChatClient chatClient;

    // Spring Boot automatically injects the configured OllamaChatClient bean
    public AIController(ChatClient.Builder chatClientBuilder) {
        // Use the builder to create the ChatClient instance
        this.chatClient = chatClientBuilder.build();
    }

    @GetMapping("/ai/chat")
    public String chat(@RequestParam(value = "prompt", defaultValue = "What is the capital of France?")
                       String prompt) {
        LOG.debug("Received prompt: {}", prompt);
        // This is the core logic: telling the LLM to respond to the prompt.
        String response = chatClient.prompt()
                .user(prompt)
                .call()
                .content();

        return response;
    }

    @PostMapping("/ai/agent")
    public String agent(@RequestBody PromptRequest request) {
        String userPrompt = request.prompt();
        LOG.info("Received POST request for Agent: {}", userPrompt);
        String responseContent = chatClient.prompt()
                .user(userPrompt)
                // Tell the LLM which functions (tools) it has access to
                .functions("getCurrentTime","getWeatherInfo")
                .call()
                .content();
        LOG.info("Agent generated response: {}", responseContent);
        return responseContent;
    }
}