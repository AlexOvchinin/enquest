package com.farmean.enquest.services.test;

import com.farmean.enquest.models.TestQuestion;
import com.farmean.enquest.services.dictionary.GlobalDictionary;
import com.farmean.enquest.services.questions.QuestionPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Component
public class SimpleTestQuestionGenerator implements TestQuestionGenerator {

    private final GlobalDictionary globalDictionary;
    private final Random random = new Random();

    @Autowired
    public SimpleTestQuestionGenerator(GlobalDictionary globalDictionary) {
        this.globalDictionary = globalDictionary;
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

        return new TestQuestion() {
            @Override
            public String getText() {
                return question;
            }

            @Override
            public List<String> getOptions() {
                return answers;
            }

            @Override
            public String correctOption() {
                return rightAnswer;
            }
        };
    }
}
