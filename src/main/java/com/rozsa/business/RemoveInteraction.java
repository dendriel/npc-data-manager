package com.rozsa.business;

import com.rozsa.model.PassiveNpcData;

public class RemoveInteraction {

    private final PassiveNpcData npc;

    private final int interactionId;

    public RemoveInteraction(PassiveNpcData npc, int interactionId) {
        this.npc = npc;
        this.interactionId = interactionId;
    }

    public void execute() {
        System.out.println("Remove interaction: " + interactionId);
        npc.removeInteractionData(interactionId);
    }
}
