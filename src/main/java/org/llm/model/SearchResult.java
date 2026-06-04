package org.llm.model;

public record SearchResult(
        FileChunk chunk,
        double score
) {
}