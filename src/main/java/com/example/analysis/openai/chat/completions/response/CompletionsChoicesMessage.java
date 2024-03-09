package com.example.analysis.openai.chat.completions.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CompletionsChoicesMessage {
    private String role;

    private String content;
}
