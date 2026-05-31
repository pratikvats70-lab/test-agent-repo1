# AGENTS.md — Agent guidance for this repository

Purpose
- Short, actionable instructions for AI coding agents to be productive in this small Java/Gradle project.

Quick facts
- Build system: Gradle (wrapper present in repo root). See `build.gradle.kts` and `settings.gradle.kts`.
- Language: Java (source root: `src/main/java`). Package root: `org.example`.
- Test framework: JUnit 5 (platform configured in `build.gradle.kts`).
- No CI or agent guidance files were found in the repo (no existing `AGENTS.md`, `.github/` agent hints, or similar).

Essential commands (Windows PowerShell)
- Build: `.\\gradlew build`  (use wrapper shipped as `gradlew.bat`)
- Test: `.\\gradlew test`
- Run compiled classes without adding plugins: `.\\gradlew build; java -cp build\\classes\\java\\main org.example.Main`
- Run packaged jar (jar produced by `jar` task): `.\\gradlew build; java -cp build\\libs\\Interview-1.0-SNAPSHOT.jar org.example.Main`

Note: this project does not apply the `application` plugin, so `gradlew run` is not available by default.

Code layout & important files
- `build.gradle.kts` — dependency and test configuration (uses JUnit BOM + Jupiter). Tests run with `useJUnitPlatform()`.
- `settings.gradle.kts` — root project name set to `Interview`.
- `src/main/java/org/example/Main.java` — program entrypoint and the only source file. Use this file to find the behavior to modify.
- `gradle/wrapper/` — use the wrapper to ensure consistent Gradle version.

Project-specific patterns and constraints
- Tests target JUnit 5 (Jupiter) and rely on the platform; when adding tests, use `org.junit.jupiter` annotations (`@Test`, `@BeforeEach`, etc.).
- Package conventions: sources are under `org.example`. New classes should follow that package unless adding separate modules.
- Dependency management is minimal: adding any third-party libraries requires editing `build.gradle.kts` and re-running the wrapper or build.
- No multi-module layout: everything lives in the single `Interview` root project.

How an AI agent should operate (step-by-step)
1. Start by reading `build.gradle.kts` and `src/main/java/org/example/Main.java` to understand available tasks and entrypoints.
2. Run `.\gradlew test` to ensure baseline tests pass before making changes (there may be none).
3. Make focused changes in `src/main/java/...` and add tests under `src/test/java/...` using JUnit 5. Example test package: `org.example`.
4. Run `.\gradlew build` and then `java -cp build\classes\java\main org.example.Main` to validate run-time behavior.
5. Avoid adding the `application` Gradle plugin unless explicitly requested; prefer running compiled classes directly.

Example prompts for editing tasks (copy/paste-friendly)
- "Change `Main.main` to accept a command-line flag `--count N` and print numbers from 1..N. Add JUnit 5 tests for the parsing logic in `src/test/java/org/example` and ensure `.\gradlew test` passes."
- "Refactor the loop in `Main` into a new `Counter` class in `org.example` with a unit-test verifying output for N=5. Update `Main` to use the new class."

References and anchors
- Entrypoint: `src/main/java/org/example/Main.java`
- Build config: `build.gradle.kts` (tests use JUnit Platform)
- Project name: `settings.gradle.kts` (rootProject.name = "Interview")
- Wrapper runtime: `gradlew.bat` (Windows) — prefer wrapper over system Gradle

Safety & repo rules (discoverable)
- No credentials or secrets are present in the repository. Do not introduce secrets or hard-coded credentials.
- Keep package names and group (`org.example`) consistent unless adding a deliberate package change; this repository uses the simple single-module layout.

If you need to extend this file
- Add CI instructions (GitHub Actions) or an `application` plugin if maintainers want `gradlew run` support.
- If adding dependencies, update `build.gradle.kts` and include the rationale in a commit message.

(End of AGENTS.md)

