package org.llm.service;

import lombok.extern.slf4j.Slf4j;
import org.llm.component.CalculatorTool;
import org.llm.component.CodeReaderTool;
import org.llm.component.CodeWriterTool;
import org.llm.component.GitHubTool;
import org.llm.component.ListFilesTool;
import org.llm.component.NotesTool;
import org.llm.component.SearchFilesTool;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OllamaService {

    private final ChatClient chatClient;

    public OllamaService(ChatClient.Builder builder, CalculatorTool calculatorTool, NotesTool notesTool,
            CodeReaderTool codeReaderTool, CodeWriterTool codeWriterTool, ListFilesTool listFilesTool,
            SearchFilesTool searchFilesTool, GitHubTool gitHubTool) {
        this.chatClient = builder
                .defaultTools(calculatorTool, notesTool, codeReaderTool, codeWriterTool, listFilesTool,
                        searchFilesTool, gitHubTool)
                .build();
    }

    public String ask(String question) {
        String response = chatClient.prompt()
                .user(question)
                .call()
                .content();
        log.info("Response from LLM : {}", response);
        return response;
    }
}