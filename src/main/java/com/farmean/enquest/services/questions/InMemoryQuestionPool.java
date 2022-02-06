package com.farmean.enquest.services.questions;

import com.farmean.enquest.models.TestQuestion;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class InMemoryQuestionPool implements QuestionPool {
    private final Map<Long, TestQuestion> pool = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(System.currentTimeMillis());

    @Override
    public long addQuestion(TestQuestion question) {
        long id = idGenerator.addAndGet(1);
        pool.put(id, question);
        return id;
    }

    @Override
    public TestQuestion getQuestion(long id) {
        return pool.get(id);
    }
}
