package org.llm.model;

public record FileChunk(
        String filePath,
        String content
) {
}