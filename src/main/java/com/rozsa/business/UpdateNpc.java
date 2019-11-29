package com.rozsa.business;

import com.rozsa.model.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.rozsa.reader.DataGetter.*;

public class UpdateNpc
{
    private final HttpServletRequest req;
    private final PassiveNpcData npc;

    public UpdateNpc(HttpServletRequest req, PassiveNpcData npc) {
        this.req = req;
        this.npc = npc;
    }

    public void execute() {
        updateGeneralData();
        updateSprite();
        updateStatus();
        updateInteractionOrder();
        updateInteractions();
    }

    private void updateGeneralData() {
        npc.setName(getString(req, "name"));
        npc.setBehaviorId(getInteger(req,"behaviorId"));
    }

    private void updateSprite() {
        SpriteData sprite = npc.getSpriteData();
        sprite.setEnabled(getBoolean(req, "spriteData_enabled"));
        sprite.setOrder(getInteger(req,"spriteData_order"));
        sprite.setImageFile(getString(req, "spriteData_imageFile"));

        Rect rect = sprite.getRect();
        rect.setX(getInteger(req,"spriteData_rect_x"));
        rect.setY(getInteger(req,"spriteData_rect_y"));
        rect.setWidth(getInteger(req,"spriteData_rect_width"));
        rect.setHeight(getInteger(req,"spriteData_rect_height"));
    }

    private void updateStatus() {
        StatusData status = npc.getStatus();
        status.setStrength(getInteger(req,"status_strength"));
        status.setIntelligence(getInteger(req,"status_intelligence"));
        status.setDexterity(getInteger(req,"status_dexterity"));
        status.setAccuracy(getInteger(req,"status_accuracy"));
        status.setLife(getInteger(req,"status_life"));
        status.setMana(getInteger(req,"status_mana"));
    }

    private void updateInteractionOrder() {
        List<Integer> interactionOrder = getIntegerList(req, "interactionOrder");
        npc.setInteractionOrder(interactionOrder);
    }

    private void updateInteractions() {
        List<InteractionData> data = getInteractionsData(req, npc);
        npc.setInteractionData(data);
    }
}
