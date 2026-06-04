package org.llm.service;

import lombok.RequiredArgsConstructor;
import org.llm.model.FileChunk;
import org.llm.model.SearchResult;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RagService {

    private final EmbeddingModel embeddingModel;
    private final ChatClient chatClient;

    private final List<FileChunk> documents = new ArrayList<>();
    private final List<float[]> vectors = new ArrayList<>();

    public String ingest() throws Exception {

        documents.clear();
        vectors.clear();

        String content =
                Files.readString(
                        Path.of("knowledge.txt")
                );

        String[] chunks =
                content.split("\\.");

        for (String chunk : chunks) {

            chunk = chunk.trim();

            if (chunk.isBlank()) {
                continue;
            }

            documents.add(
                    new FileChunk(
                            "knowledge.txt",
                            chunk
                    )
            );

            vectors.add(
                    embeddingModel.embed(chunk)
            );
        }

        return "Chunks ingested : "
                + documents.size();
    }

    public String ingestCodebase(String rootPath)
            throws Exception {

        documents.clear();
        vectors.clear();

        Files.walk(Path.of(rootPath))
                .filter(Files::isRegularFile)
                .filter(path ->
                        path.toString().endsWith(".java"))
                .forEach(path -> {

                    try {

                        String content =
                                Files.readString(path);

                        documents.add(
                                new FileChunk(
                                        path.toString(),
                                        content
                                )
                        );

                        vectors.add(
                                embeddingModel.embed(content)
                        );

                    } catch (Exception e) {

                        throw new RuntimeException(e);
                    }
                });

        return "Indexed Files : "
                + documents.size();
    }

    public String search(String query) {

        SearchResult result =
                findBestMatch(query);

        return """
                Chunk:
                %s

                Source:
                %s

                Similarity:
                %.4f
                """.formatted(
                result.chunk().content(),
                result.chunk().filePath(),
                result.score()
        );
    }

    public String askRag(String query) {

        SearchResult bestMatch =
                findBestMatch(query);

        String context =
                findRelevantContext(query);

        String answer =
                chatClient.prompt()
                        .user("""
                                You are analyzing Java source code.

                                Answer strictly using the provided code context.

                                If a method returns a value,
                                explicitly mention the returned value.

                                If the answer is not present in the code context,
                                reply:
                                I don't know based on the provided code.

                                Context:
                                %s

                                Question:
                                %s
                                """.formatted(
                                context,
                                query
                        ))
                        .call()
                        .content();

        return """
                CONTEXT:
                %s

                ANSWER:
                %s

                SOURCE:
                %s

                SIMILARITY:
                %.4f
                """.formatted(
                context,
                answer,
                bestMatch.chunk().filePath(),
                bestMatch.score()
        );
    }

    private SearchResult findBestMatch(
            String query) {

        float[] queryVector =
                embeddingModel.embed(query);

        SearchResult bestResult = null;

        double bestScore = -1;

        for (int i = 0; i < vectors.size(); i++) {

            double score =
                    cosineSimilarity(
                            queryVector,
                            vectors.get(i)
                    );

            if (score > bestScore) {

                bestScore = score;

                bestResult =
                        new SearchResult(
                                documents.get(i),
                                score
                        );
            }
        }

        return bestResult;
    }

    private String findRelevantContext(
            String query) {

        float[] queryVector =
                embeddingModel.embed(query);

        List<SearchResult> results =
                new ArrayList<>();

        for (int i = 0; i < vectors.size(); i++) {

            double score =
                    cosineSimilarity(
                            queryVector,
                            vectors.get(i)
                    );

            results.add(
                    new SearchResult(
                            documents.get(i),
                            score
                    )
            );
        }

        results.sort(
                Comparator.comparingDouble(
                        SearchResult::score
                ).reversed()
        );

        return results.stream()
                .limit(3)
                .map(result ->
                        result.chunk().content())
                .collect(
                        Collectors.joining(
                                "\n\n"
                        )
                );
    }

    private double cosineSimilarity(
            float[] a,
            float[] b) {

        double dot = 0.0;
        double normA = 0.0;
        double normB = 0.0;

        for (int i = 0; i < a.length; i++) {

            dot += a[i] * b[i];

            normA += a[i] * a[i];

            normB += b[i] * b[i];
        }

        return dot /
                (Math.sqrt(normA)
                        * Math.sqrt(normB));
    }
}