package com.farmean.enquest.models;

import java.util.List;

public interface TestQuestion {
    long getId();

    String getText();

    List<String> getOptions();

    String correctOption();
}
