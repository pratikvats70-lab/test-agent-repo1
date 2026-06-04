package org.llm.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class EmbeddingController {

    private final EmbeddingModel embeddingModel;

    @GetMapping("/embed")
    public String embed() {

        float[] vector =
                embeddingModel.embed(
                        "Kafka is a distributed event streaming platform"
                );

        return "Embedding Dimensions = "
                + vector.length;
    }
}