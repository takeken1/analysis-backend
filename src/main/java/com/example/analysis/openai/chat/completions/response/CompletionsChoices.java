package com.example.analysis.openai.chat.completions.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CompletionsChoices {
    private Integer index;

    private CompletionsChoicesMessage message;

    private Integer logprobs;

    @JsonProperty("finish_reason")
    private String finishReason;
}
