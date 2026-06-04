package org.llm.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

@Component
@Slf4j
public class CodeReaderTool {

    @Tool(description = "Read source code file from filesystem")
    public String readCode(String filePath) throws Exception {

        log.info("CodeReaderTool invoked");
        log.info("filePath = {}", filePath);

        if (filePath == null || filePath.isBlank()) {
            throw new IllegalArgumentException("filePath must be provided");
        }

        Path path = Path.of(filePath);

        if (!Files.exists(path)) {
            throw new FileNotFoundException(
                    "File not found: " + path.toAbsolutePath()
            );
        }

        String content = Files.readString(
                path,
                StandardCharsets.UTF_8
        );

        log.info("Successfully read file {}", path.toAbsolutePath());

        return content;
    }
}