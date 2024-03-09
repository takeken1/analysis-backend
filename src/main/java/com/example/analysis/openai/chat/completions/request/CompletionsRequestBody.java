package com.example.analysis.openai.chat.completions.request;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@AllArgsConstructor
@Getter
@Setter
public class CompletionsRequestBody {
    private String model;

    private List<CompletionsMessage> messages;
}
