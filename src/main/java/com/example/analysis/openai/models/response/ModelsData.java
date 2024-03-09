package com.example.analysis.openai.models.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ModelsData {
    private String id;

    private String object;

    private Long created;

    @JsonProperty("owned_by")
    private String ownedBy;
}
