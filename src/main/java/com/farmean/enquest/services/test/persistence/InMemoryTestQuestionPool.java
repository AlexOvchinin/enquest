package com.farmean.enquest.services.test.persistence;

import com.farmean.enquest.models.TestQuestion;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class InMemoryTestQuestionPool implements TestQuestionPool {
    private final Map<Long, TestQuestion> pool = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(System.currentTimeMillis());

    @Override
    public TestQuestion addQuestion(String question, List<String> options, String rightOption) {
        long id = idGenerator.addAndGet(1);
        TestQuestion testQuestion = new TestQuestion() {
            @Override
            public long getId() {
                return id;
            }

            @Override
            public String getText() {
                return question;
            }

            @Override
            public List<String> getOptions() {
                return options;
            }

            @Override
            public String correctOption() {
                return rightOption;
            }
        };
        pool.put(id, testQuestion);
        return testQuestion;
    }

    @Override
    public TestQuestion getQuestion(long id) {
        return pool.get(id);
    }
}
