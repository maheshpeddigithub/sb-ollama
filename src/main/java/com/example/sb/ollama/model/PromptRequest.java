package com.example.sb.ollama.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Setter
@Getter
@AllArgsConstructor
public class PromptRequest {
    String promptMessage;
    ArrayList<String> items;
}
