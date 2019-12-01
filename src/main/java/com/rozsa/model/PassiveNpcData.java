package com.rozsa.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PassiveNpcData extends NpcData {
    private List<Integer> interactionOrder;

    private List<InteractionData> interactionData;

    private int currMessageId;

    public PassiveNpcData() {}

    public PassiveNpcData(int id) {
        super(id);
        interactionOrder = new ArrayList<>();
        interactionData = new ArrayList<>();
        currMessageId = 0;
    }

    public int getCurrMessageId() {
        return currMessageId;
    }

    public void setCurrMessageId(int currMessageId) {
        this.currMessageId = currMessageId;
    }

    public List<InteractionData> getInteractionData()
    {
        List<InteractionData> orderedInteractionData = interactionOrder
                .stream()
                .map(id -> getInteractionDataByid(id))
                .collect(Collectors.toList());

        return orderedInteractionData;
    }

    @JsonIgnore
    public InteractionData getInteractionDataByid(int id) {
        return interactionData
                .stream()
                .filter(i -> i.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @JsonIgnore
    public List<Integer> getInteractionMessagesIds() {
        ArrayList<Integer> allMessagesIds = new ArrayList<>();
        interactionData.forEach(i -> allMessagesIds.addAll(i.getMessagesIds()));
        return allMessagesIds;
    }

    public void removeInteractionMessage(int id) {
        InteractionData targetInteraction = interactionData
                                                .stream()
                                                .filter(i -> i.containsMessageId(id))
                                                .findFirst()
                                                .orElse(null);

        targetInteraction.removeMessage(id);
    }

    public void addMessageToInteraction(int interactionId) {
        InteractionData interaction = getInteractionDataByid(interactionId);
        int newMessageId = findNextMessageId();
        interaction.addMessage(newMessageId);
    }

    public List<Integer> getInteractionOrder()
    {
        return interactionOrder;
    }

    @JsonIgnore
    public String getInteractionOrderAsText()
    {
        return interactionOrder
                .toString()
                .replace("[", "")
                .replace("]", "");
    }

    public void setInteractionData(List<InteractionData> interactionData)
    {
        this.interactionData = interactionData;
    }

    public void setInteractionOrder(List<Integer> interactionOrder)
    {
        this.interactionOrder = interactionOrder;
    }

    public void removeInteractionData(int id) {
        interactionData.removeIf(i -> i.getId() == id);
        interactionOrder.removeIf(i -> i == id);
    }

    public InteractionData addInteractionData() {
        int nextId = findNextInteractionDataId();
        InteractionData newData = new InteractionData(nextId);
        interactionData.add(newData);
        interactionOrder.add(nextId);

        return newData;
    }

    private int findNextInteractionDataId() {
        int highestId = 0;
        for (InteractionData data : interactionData) {
            int currId = data.getId();
            if (currId > highestId) {
                highestId = currId;
            }
        }

        return highestId + 1;
    }

    private int findNextMessageId() {
        int newMessageId = currMessageId + 1;
        currMessageId++;
        return newMessageId;
        /* It is all about clarity. We could have used just this line:
         * return ++newMessageIndex;
         * but it is harder to read and we aren't short of coding lines =]
         */
    }
}
