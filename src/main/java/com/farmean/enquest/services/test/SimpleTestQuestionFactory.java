package com.farmean.enquest.services.test;

import com.farmean.enquest.models.TestQuestion;
import com.farmean.enquest.services.test.strategy.TestQuestionGeneratorStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class SimpleTestQuestionFactory implements TestQuestionFactory {

    private final TestQuestionGeneratorStrategy strategy;
    private final Random random = new Random();

    @Autowired
    public SimpleTestQuestionFactory(TestQuestionGeneratorStrategy strategy) {
        this.strategy = strategy;
    }

    @Override
    public TestQuestion generate() {
        return strategy.generate();
    }
}
