package com.farmean.enquest.services.dictionary;

import com.google.common.collect.ImmutableMap;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class InMemoryGlobalDictionary implements GlobalDictionary {

    private final Map<String, String> words = new HashMap<>();

    public InMemoryGlobalDictionary() throws IOException {
        loadCSV();
    }

    private void loadCSV() throws IOException {
        var resource = ResourceUtils.getURL("classpath:static/initial_dictionary.csv");

        try (Reader in = new InputStreamReader(resource.openStream())) {
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(in);
            for (var record : records) {
                if (record.size() < 2) {
                    continue;
                }
                String key = record.get(0);

                List<String> values = new ArrayList<>();
                for (int i = 1; i < record.size(); ++i) {
                    values.add(record.get(i));
                }

                String value = StringUtils.join(values, ',');

                words.put(key, value);
            }
        }

    }

    @Override
    public void add(String word, String translation) {

    }

    @Override
    public Map<String, String> getWords() {
        return words;
    }
}
