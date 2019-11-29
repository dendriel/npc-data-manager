package com.rozsa.business;

import com.rozsa.model.PassiveNpcsDataHolder;

public class RemoveNpc {
    private final PassiveNpcsDataHolder dataHolder;

    private final int id;

    public RemoveNpc(PassiveNpcsDataHolder dataHolder, int id) {
        this.dataHolder = dataHolder;
        this.id = id;
    }

    public void execute() {
        dataHolder.removeNpc(id);
    }
}
