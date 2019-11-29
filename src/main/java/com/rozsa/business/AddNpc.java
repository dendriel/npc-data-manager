package com.rozsa.business;

import com.rozsa.model.PassiveNpcData;
import com.rozsa.model.PassiveNpcsDataHolder;

public class AddNpc {
    private final PassiveNpcsDataHolder dataHolder;

    public AddNpc(PassiveNpcsDataHolder dataHolder) {
        this.dataHolder = dataHolder;
    }

    public void execute() {
        int newNpcId = dataHolder.findNextNpcId();
        PassiveNpcData npc = new PassiveNpcData(newNpcId);
        dataHolder.addNpc(npc);
    }
}
