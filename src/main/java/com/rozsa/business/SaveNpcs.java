package com.rozsa.business;

import com.rozsa.model.PassiveNpcsDataHolder;
import com.rozsa.reader.JsonWriter;

import java.io.IOException;

public class SaveNpcs {
    private final String dataFilePath;

    private final PassiveNpcsDataHolder dataHolder;

    public SaveNpcs(String dataFilePath, PassiveNpcsDataHolder dataHolder) {
        this.dataFilePath = dataFilePath;
        this.dataHolder = dataHolder;
    }

    public void execute() throws IOException {
        JsonWriter writer = new JsonWriter(dataFilePath);
        writer.write(dataHolder);
    }
}
