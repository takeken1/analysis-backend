package com.example.analysis.openai;

import java.time.Duration;
import java.util.List;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.example.analysis.openai.chat.completions.request.CompletionsMessage;
import com.example.analysis.openai.chat.completions.request.CompletionsRequestBody;
import com.example.analysis.openai.chat.completions.response.CompletionsResponse;
import com.example.analysis.openai.models.response.ModelsResponse;

import lombok.extern.slf4j.Slf4j;

/**
 * OpenAI API client.
 */
@Slf4j
public class OpenAI {

    private String apiKey;
    private String apiEndpoint;
    private RestTemplate restTemplate;

    public OpenAI(String apiKey, String baseUrl, String apiEndpoint) {
        this.apiKey = apiKey;
        this.apiEndpoint = baseUrl + apiEndpoint;
        this.restTemplate = new RestTemplateBuilder()
                .setConnectTimeout(Duration.ofSeconds(10))
                .setReadTimeout(Duration.ofSeconds(10)).build();
    }

    public OpenAI(String apiKey, String baseUrl, String apiEndpoint, RestTemplate restTemplate) {
        this.apiKey = apiKey;
        this.apiEndpoint = baseUrl + apiEndpoint;
        this.restTemplate = restTemplate;
    }

    /**
     * Call models.
     * @return {@link ModelsResponse} ModelsResponse
     */
    public ModelsResponse models() {
        HttpHeaders headers = createHeaders();

        HttpEntity<String> httpRequest = new HttpEntity<>(headers);
        try {
            log.info("request send");

            ResponseEntity<ModelsResponse> response = restTemplate.exchange(apiEndpoint, HttpMethod.GET, httpRequest,
                    ModelsResponse.class);

            log.info("response receive: {}", response);

            return response.getBody();
        } catch (RestClientException e) {
            throw new RuntimeException("Failed to call OpenAI API", e);
        }
    }

    /**
     * Call chat completions.
     * @param prompt Prompt
     * @return {@link CompletionsResponse} CompletionsResponse
     */
    public CompletionsResponse completions(final String prompt) {
        HttpHeaders headers = createHeaders();

        CompletionsMessage systemMessage = CompletionsMessage.builder()
                .role("system")
                .content("You are a helpful assistant.")
                .build();
        CompletionsMessage userMessage = CompletionsMessage.builder()
                .role("user")
                .content(prompt)
                .build();
        CompletionsRequestBody body = CompletionsRequestBody.builder()
                .model("gpt-3.5-turbo")
                .messages(List.of(systemMessage, userMessage))
                .build();

        RequestEntity<CompletionsRequestBody> request = RequestEntity
                .post(apiEndpoint)
                .headers(headers)
                .body(body);

        try {
            log.info("request send");

            ResponseEntity<CompletionsResponse> response = restTemplate.postForEntity(
                    apiEndpoint, request, CompletionsResponse.class);

            log.info("response receive: {}", response);

            return response.getBody();
        } catch (RestClientException e) {
            throw new RuntimeException("Failed to call OpenAI API", e);
        }
    }

    private HttpHeaders createHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + apiKey);
        return headers;
    }
}
