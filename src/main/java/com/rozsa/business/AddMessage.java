package com.rozsa.business;

import com.rozsa.model.PassiveNpcData;

public class AddMessage {
    private final PassiveNpcData npc;

    private final int interactionId;

    public AddMessage(PassiveNpcData npc, int interactionId) {
        this.npc = npc;
        this.interactionId = interactionId;
    }

    public void execute() {
        npc.addMessageToInteraction(interactionId);
    }
}
