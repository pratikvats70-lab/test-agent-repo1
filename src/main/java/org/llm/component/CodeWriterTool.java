package org.llm.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

@Slf4j
@Component
public class CodeWriterTool {

    @Tool(description = "Write content to any file path on the filesystem")
    public String writeFile(String absoluteFilePath,
                            String content) {
        log.info("Writer tool Invoked");
        try {

            Path path = Path.of(absoluteFilePath);

            if (path.getParent() != null) {
                Files.createDirectories(path.getParent());
            }

            Files.writeString(
                    path,
                    content,
                    StandardCharsets.UTF_8
            );

            log.info("Updated file: {}", path.toAbsolutePath());

            return "Successfully updated file: "
                    + path.toAbsolutePath();

        } catch (Exception e) {

            log.error("Error writing file", e);

            return "Failed to write file: "
                    + e.getMessage();
        }
    }
}