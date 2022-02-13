package com.farmean.enquest.services.test.strategy;

import com.farmean.enquest.models.TestQuestion;
import com.farmean.enquest.services.test.generators.TestQuestionGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@Component
public class RandomTestQuestionGeneratorStrategy implements TestQuestionGeneratorStrategy {
    private static final Logger LOGGER = LoggerFactory.getLogger(RandomTestQuestionGeneratorStrategy.class);

    private final List<TestQuestionGenerator> testQuestionGenerators;

    private final Random random = new Random();

    @Autowired
    public RandomTestQuestionGeneratorStrategy(List<TestQuestionGenerator> testQuestionGenerators) {
        this.testQuestionGenerators = testQuestionGenerators;
    }

    @Override
    public TestQuestion generate() {
        int generatorIndex = random.nextInt(testQuestionGenerators.size());
        LOGGER.info("Generating by using generator {}, {}", generatorIndex, testQuestionGenerators.get(generatorIndex));
        return testQuestionGenerators.get(generatorIndex).generate();
    }
}
