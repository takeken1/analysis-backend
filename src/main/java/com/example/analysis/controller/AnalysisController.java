package com.example.analysis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.analysis.form.AnalysisForm;
import com.example.analysis.openai.chat.completions.response.CompletionsResponse;
import com.example.analysis.service.AnalysisService;

@RestController
@CrossOrigin
@RequestMapping("/api/analysis")
public class AnalysisController {
    @Autowired
    private AnalysisService analysisService;

    @GetMapping("/models")
    public ResponseEntity<?> models() {
        return ResponseEntity.ok(analysisService.models());
    }

    @PostMapping("/chat")
    public ResponseEntity<?> chat(@RequestBody AnalysisForm form) {
        CompletionsResponse response = analysisService.chat(form);
        return ResponseEntity.ok(response);
    }
}
