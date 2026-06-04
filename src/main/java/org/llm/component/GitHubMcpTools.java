package org.llm.component;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

@Component
public class GitHubMcpTools {

    @Tool(description = "Get repository name")
    public String repoInfo() {
        return "Spring";
    }
}