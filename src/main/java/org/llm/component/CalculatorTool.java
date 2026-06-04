package org.llm.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CalculatorTool {

    @Tool(description = "Add two numbers")
    public int add(int a, int b) {
        log.info("Calculator tool invoked");
        return a + b;
    }
}