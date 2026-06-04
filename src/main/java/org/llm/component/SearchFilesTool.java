package org.llm.component;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;

@Component
@Slf4j
public class SearchFilesTool {

    @Tool(description = "Search files by name in a directory")
    public String searchFiles(String rootDirectory,
                              String fileName) {
        log.info("SearchFiles Tool Invoked");
        try {

            return Files.walk(Path.of(rootDirectory))
                    .filter(Files::isRegularFile)
                    .filter(f ->
                            f.getFileName()
                                    .toString()
                                    .toLowerCase()
                                    .contains(fileName.toLowerCase()))
                    .map(Path::toString)
                    .collect(Collectors.joining("\n"));

        } catch (Exception e) {

            log.error("Search error", e);

            return e.getMessage();
        }
    }
}
