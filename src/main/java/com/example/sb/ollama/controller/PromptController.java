package com.example.sb.ollama.controller;

import com.example.sb.ollama.model.PromptRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.Generation;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class PromptController {

    private final static Logger log = LoggerFactory.getLogger(PromptController.class);
    private final ChatModel chatModel;

    public PromptController(ChatModel chatModel) {
        this.chatModel = chatModel;
    }

    @PostMapping("/get-dish")
    public String getDishByItems(@RequestBody PromptRequest promptRequest) {
        String promptMessage = promptRequest.getPromptMessage();
        ArrayList<String> items = promptRequest.getItems();
        String itemsCommaSeparated = String.join(", ", items);
        PromptTemplate promptTemplate = new PromptTemplate(promptMessage);
        Message message = promptTemplate.createMessage(Map.of("items", itemsCommaSeparated));
        Prompt prompt = new Prompt(List.of(message));
        log.info("prompt : {}", prompt);
        Generation generation = chatModel.call(prompt).getResult();
        log.info("AI Response: {}", generation);
        return generation.getOutput().getText();
    }
}
