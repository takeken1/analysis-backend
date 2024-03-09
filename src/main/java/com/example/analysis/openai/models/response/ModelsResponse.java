package com.example.analysis.openai.models.response;

import java.util.List;

import lombok.Data;

@Data
public class ModelsResponse {
    private String object;

    private List<ModelsData> data;
}
