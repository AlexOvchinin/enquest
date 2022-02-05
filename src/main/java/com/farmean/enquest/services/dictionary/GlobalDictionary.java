package com.farmean.enquest.services.dictionary;

import java.util.Map;

public interface GlobalDictionary {
    void add(String word, String translation);

    Map<String, String> getWords();
}
