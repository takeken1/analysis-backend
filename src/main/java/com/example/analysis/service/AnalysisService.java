package com.example.analysis.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.analysis.form.AnalysisForm;
import com.example.analysis.openai.OpenAI;
import com.example.analysis.openai.chat.completions.response.CompletionsChoices;
import com.example.analysis.openai.chat.completions.response.CompletionsChoicesMessage;
import com.example.analysis.openai.chat.completions.response.CompletionsResponse;
import com.example.analysis.openai.models.response.ModelsResponse;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AnalysisService {
    @Value("${openai.api.key}")
    private String API_KEY;

    @Value("${openai.api.url}")
    private String API_URL;

    @Value("${openai.api.endpoint.completions}")
    private String ENDPOINT_COMPLETIONS;

    @Value("${openai.api.endpoint.models}")
    private String ENDPOINT_MODELS;

    public ModelsResponse models() {
        OpenAI openai = new OpenAI(API_KEY, API_URL, ENDPOINT_MODELS);

        ModelsResponse response = openai.models();

        return response;
    }

    /**
     * Chat with OpenAI.
     * @param form {@link AnalysisForm} AnalysisForm
     * @return {@link CompletionsResponse} CompletionsResponse
     */
    public CompletionsResponse chat(AnalysisForm form) {
        String message = form.getMessage();

        OpenAI openai = new OpenAI(API_KEY, API_URL, ENDPOINT_COMPLETIONS);

        CompletionsResponse response = openai.completions(message);

        response.getChoices().stream()
                .map(CompletionsChoices::getMessage)
                .map(CompletionsChoicesMessage::getContent)
                .forEach(log::info);

        return response;
    }
}
