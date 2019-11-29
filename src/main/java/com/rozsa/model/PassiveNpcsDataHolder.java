package com.rozsa.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public class PassiveNpcsDataHolder
{
    private List<PassiveNpcData> data;

    public List<PassiveNpcData> getData()
    {
        return data;
    }

    public void setData(List<PassiveNpcData> data)
    {
        this.data = data;
    }

    public void addNpc(PassiveNpcData npc) {
        data.add(npc);
    }

    public void removeNpc(int id) {
        data.removeIf(n -> n.getId() == id);
    }

    public PassiveNpcData getNpcByName(String name) {
        return data
                .stream()
                .filter(npc -> npc.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    public PassiveNpcData getNpcById(Integer id) {
        return data
                .stream()
                .filter(npc -> npc.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @JsonIgnore
    public int findNextNpcId() {
        int highestId = 0;
        for (NpcData npc : data) {
            int currId = npc.getId();
            if (currId > 0) {
                highestId = currId;
            }
        }

        return highestId + 1;
    }
}
