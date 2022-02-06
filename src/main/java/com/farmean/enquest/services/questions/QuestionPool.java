package com.farmean.enquest.services.questions;

import com.farmean.enquest.models.TestQuestion;

import javax.annotation.Nullable;

public interface QuestionPool {
    long addQuestion(TestQuestion question);

    @Nullable
    TestQuestion getQuestion(long id);
}
