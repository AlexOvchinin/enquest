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
public class RuEngTestQuestionGenerator implements TestQuestionGenerator {

    private final Random random = new Random();
    private final GlobalDictionary globalDictionary;
    private final TestQuestionPool testQuestionPool;

    public RuEngTestQuestionGenerator(GlobalDictionary globalDictionary, TestQuestionPool testQuestionPool) {
        this.globalDictionary = globalDictionary;
        this.testQuestionPool = testQuestionPool;
    }

    @Override
    public TestQuestion generate() {
        Map<String, String> words = globalDictionary.getWords();

        List<String> keys = new ArrayList<>(words.keySet());
        List<String> values = new ArrayList<>(words.values());

        int questionIndex = random.nextInt(keys.size());

        String question = values.get(questionIndex);
        String rightOption = keys.get(questionIndex);

        List<String> options = new ArrayList<>();
        options.add(rightOption);

        for (int i = 0; i < 3; ++i) {
            options.add(keys.get(random.nextInt(keys.size())));
        }

        Collections.shuffle(options);

        return testQuestionPool.addQuestion(question, options, rightOption);
    }
}
