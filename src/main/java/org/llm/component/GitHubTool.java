package org.llm.component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kohsuke.github.GHContent;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class GitHubTool {

    @Value("${github.token}")
    private String token;

    @Tool(description = "Read README from a GitHub repository")
    public String readReadme(String repoName) throws Exception {

        GitHub github = GitHub.connectUsingOAuth(token);

        GHRepository repository =
                github.getRepository(repoName);

        return repository
                .getReadme()
                .getContent();
    }

    @Tool(description = "Create a new branch in a GitHub repository")
    public String createBranch(String repoName,
            String branchName) {

        try {

            GitHub github =
                    GitHub.connectUsingOAuth(token);

            GHRepository repository =
                    github.getRepository(repoName);

            String defaultBranch =
                    repository.getDefaultBranch();

            String sha =
                    repository
                            .getBranch(defaultBranch)
                            .getSHA1();

            repository.createRef(
                    "refs/heads/" + branchName,
                    sha
            );

            return "Branch created successfully: "
                    + branchName;

        } catch (Exception e) {

            log.error("Error creating branch", e);

            return "Error creating branch: "
                    + e.getMessage();
        }
    }

    @Tool(description = "Create or update a file in GitHub repository")
    public String createFile(String repoName,
            String branchName,
            String filePath,
            String content) {

        try {

            GitHub github =
                    GitHub.connectUsingOAuth(token);

            GHRepository repository =
                    github.getRepository(repoName);

            repository.createContent()
                    .branch(branchName)
                    .path(filePath)
                    .content(content)
                    .message("Created by AI Agent")
                    .commit();

            return "File created successfully";

        } catch (Exception e) {

            log.error("Error creating file", e);

            return e.getMessage();
        }
    }

    @Tool(description = "Create pull request")
    public String createPullRequest(String repoName,
            String title,
            String body,
            String headBranch,
            String baseBranch) {

        try {

            GitHub github =
                    GitHub.connectUsingOAuth(token);

            GHRepository repository =
                    github.getRepository(repoName);

            var pr = repository.createPullRequest(
                    title,
                    headBranch,
                    baseBranch,
                    body
            );

            return pr.getHtmlUrl().toString();

        } catch (Exception e) {

            log.error("Error creating PR", e);

            return e.getMessage();
        }
    }
    @Tool(description = "Update an existing file in a GitHub repository branch")
    public String updateFile(
            String repoName,
            String branchName,
            String filePath,
            String newContent) {

        try {

            GitHub github =
                    GitHub.connectUsingOAuth(token);
            log.info("repoName = {}", repoName);
            log.info("branchName = {}", branchName);
            log.info("filePath = {}", filePath);
            GHRepository repository =
                    github.getRepository(repoName);
            GHContent content =
                    repository.getFileContent(
                            filePath,
                            branchName
                    );

            content.update(
                    newContent,
                    "Updated by AI Agent",
                    branchName
            );

            return "File updated successfully";

        } catch (Exception e) {

            log.error("Error updating file", e);

            return e.getMessage();
        }
    }
}