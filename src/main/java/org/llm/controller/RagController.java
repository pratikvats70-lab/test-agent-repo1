package org.llm.controller;

import lombok.RequiredArgsConstructor;
import org.llm.service.RagService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RagController {

    private final RagService ragService;

    @GetMapping("/ingest")
    public String ingest() throws Exception {
        return ragService.ingest();
    }

    @GetMapping("/ingest-codebase")
    public String ingestCodebase(
            @RequestParam String path)
            throws Exception {

        return ragService.ingestCodebase(path);
    }

    @GetMapping("/search")
    public String search(
            @RequestParam String q) {

        return ragService.search(q);
    }

    @GetMapping("/ask-rag")
    public String askRag(
            @RequestParam String q) {

        return ragService.askRag(q);
    }
}