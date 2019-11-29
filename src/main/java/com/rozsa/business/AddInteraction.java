package com.rozsa.business;

import com.rozsa.model.PassiveNpcData;

public class AddInteraction {

    private final PassiveNpcData npc;

    public AddInteraction(PassiveNpcData npc) {
        this.npc = npc;
    }

    public void execute() {
        npc.addInteractionData();
    }
}
