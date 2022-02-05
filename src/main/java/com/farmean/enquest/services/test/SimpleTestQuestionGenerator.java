package com.farmean.enquest.services.test;

import com.farmean.enquest.models.TestQuestion;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
public class SimpleTestQuestionGenerator implements TestQuestionGenerator {
    @Override
    public TestQuestion generate() {
        return new TestQuestion() {
            @Override
            public String getText() {
                return "go";
            }

            @Override
            public Collection<String> getOptions() {
                return List.of(
                        "идти",
                        "скакать",
                        "бежать",
                        "смотреть"
                );
            }
        };
    }
}
