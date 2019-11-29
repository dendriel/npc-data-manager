package com.rozsa.business;

import com.rozsa.model.PassiveNpcsDataHolder;
import com.rozsa.reader.JsonReader;

public class LoadNpcs {
    private final String filePath;

    public LoadNpcs(String filePath) {
        this.filePath = filePath;
    }

    public PassiveNpcsDataHolder execute() {
        JsonReader<PassiveNpcsDataHolder> reader = new JsonReader<>(filePath, PassiveNpcsDataHolder.class);
        try {
            return reader.read();

        } catch (Exception ex) {
            System.out.printf("Failed to read data from %s", filePath);
            return new PassiveNpcsDataHolder();
        }
    }
}
