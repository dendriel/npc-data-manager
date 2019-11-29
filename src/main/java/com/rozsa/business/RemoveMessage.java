package com.rozsa.business;

import com.rozsa.model.PassiveNpcData;

public class RemoveMessage {
    private final PassiveNpcData npc;

    private final int messageId;

    public RemoveMessage(PassiveNpcData npc, int messageId) {
        this.npc = npc;
        this.messageId = messageId;
    }

    public void execute() {
        System.out.println("Remove message: " + messageId);
        npc.removeInteractionMessage(messageId);
    }
}
