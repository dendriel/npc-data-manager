package com.rozsa.model;

public class NpcData
{
    private int id;

    private String name;

    private int behaviorId;

    private boolean isFacingRight;

    private StatusData status;

    private SpriteData spriteData;

    public NpcData() {}

    public NpcData(int id) {
        this.id = id;
        status = new StatusData();
        spriteData = new SpriteData();
        name = "FIX ME";
        behaviorId = 2;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getBehaviorId()
    {
        return behaviorId;
    }

    public void setBehaviorId(int behaviorId)
    {
        this.behaviorId = behaviorId;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public StatusData getStatus()
    {
        return status;
    }

    public void setStatus(StatusData status)
    {
        this.status = status;
    }

    public SpriteData getSpriteData()
    {
        return spriteData;
    }

    public void setSpriteData(SpriteData spriteData)
    {
        this.spriteData = spriteData;
    }

    @Override
    public String toString() {
        return name;
    }

    public boolean isFacingRight() {
        return isFacingRight;
    }

    public void setFacingRight(boolean facingRight) {
        isFacingRight = facingRight;
    }
}
