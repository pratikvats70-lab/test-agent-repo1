package org.llm.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;

@Component
@Slf4j
public class ListFilesTool {

    @Tool(description = "List all files and folders inside a directory")
    public String listFiles(String directoryPath) {

        try {

            log.info("ListFilesTool invoked");
            log.info("directoryPath = {}", directoryPath);

            Path path = Path.of(directoryPath);

            if (!Files.exists(path)) {
                return "Directory does not exist: " + path.toAbsolutePath();
            }

            if (!Files.isDirectory(path)) {
                return "Path is not a directory: " + path.toAbsolutePath();
            }

            String files = Files.walk(path)
                    .limit(100)
                    .map(Path::toString)
                    .collect(Collectors.joining("\n"));

            log.info("Files found:\n{}", files);

            return files;

        } catch (Exception e) {

            log.error("Error listing files", e);

            return "Error listing files: " + e.getMessage();
        }
    }
}