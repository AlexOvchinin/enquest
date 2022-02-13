package com.farmean.enquest.services.test.generators;

import com.farmean.enquest.models.TestQuestion;
import com.farmean.enquest.services.dictionary.GlobalDictionary;
import com.farmean.enquest.services.test.persistence.TestQuestionPool;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Component
public class EngRuTestQuestionGenerator implements TestQuestionGenerator {

    private final Random random = new Random();
    private final GlobalDictionary globalDictionary;
    private final TestQuestionPool testQuestionPool;

    public EngRuTestQuestionGenerator(GlobalDictionary globalDictionary, TestQuestionPool testQuestionPool) {
        this.globalDictionary = globalDictionary;
        this.testQuestionPool = testQuestionPool;
    }

    @Override
    public TestQuestion generate() {
        Map<String, String> words = globalDictionary.getWords();

        List<String> keys = new ArrayList<>(words.keySet());
        List<String> values = new ArrayList<>(words.values());

        String question = keys.get(random.nextInt(keys.size()));
        String rightAnswer = words.get(question);

        List<String> answers = new ArrayList<>();
        answers.add(rightAnswer);

        for (int i = 0; i < 3; ++i) {
            answers.add(values.get(random.nextInt(values.size())));
        }

        Collections.shuffle(answers);

        return testQuestionPool.addQuestion(question, answers, rightAnswer);
    }
}
