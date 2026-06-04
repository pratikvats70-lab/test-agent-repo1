package org.llm.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Path;

@Component
@Slf4j
public class NotesTool {

    @Tool(description = "Read notes from interview-notes.txt file")
    public String readNotes() throws Exception {
        log.info("Notes tool Invoked");
        ClassPathResource resource =
                new ClassPathResource("llm/interview-notes.txt");
        String data = new String(
                resource.getInputStream().readAllBytes());
        log.info("FILE CONTENT: {}", data);
        return data;
    }
}