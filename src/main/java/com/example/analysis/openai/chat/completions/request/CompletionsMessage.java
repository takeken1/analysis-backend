package com.example.analysis.openai.chat.completions.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@AllArgsConstructor
@Getter
@Setter
public class CompletionsMessage {
    private String role;

    private String content;
}
