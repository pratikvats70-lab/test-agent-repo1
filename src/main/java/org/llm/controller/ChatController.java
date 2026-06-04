package org.llm.controller;

import lombok.extern.slf4j.Slf4j;
import org.llm.service.OllamaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Path;

@Slf4j
@RestController
public class ChatController {

    private final OllamaService ollamaService;

    public ChatController(OllamaService ollamaService) {
        this.ollamaService = ollamaService;
    }

    @GetMapping("/ask")
    public String ask(@RequestParam String q) {

        log.info("Question received : {}", q);

        String response = ollamaService.ask(q);

        log.info("Response : {}", response);

        return response;
    }

    @GetMapping("/test")
    public String test() {
        log.info("Notes tool Invoked");
        Path path = Path.of("llm/interview-notes.txt");
        log.info("Absolute Path {} " , path.toAbsolutePath());
        return "OK";
    }
}