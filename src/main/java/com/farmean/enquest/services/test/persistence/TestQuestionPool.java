package com.farmean.enquest.services.test.persistence;

import com.farmean.enquest.models.TestQuestion;

import javax.annotation.Nullable;
import java.util.List;

public interface TestQuestionPool {
    TestQuestion addQuestion(String question, List<String> options, String rightOption);

    @Nullable
    TestQuestion getQuestion(long id);
}
