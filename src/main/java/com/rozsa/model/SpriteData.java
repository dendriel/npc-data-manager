package com.rozsa.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class SpriteData
{
    private String imageFile;

    private int order;

    private Rect rect;

    private boolean enabled;

    public SpriteData() {
        rect = new Rect(0,0,64,64);
        enabled = true;
        order = 0;
    }

    public String getImageFile()
    {
        return imageFile;
    }

    public void setImageFile(String imageFile)
    {
        this.imageFile = imageFile;
    }

    public int getOrder()
    {
        return order;
    }

    public void setOrder(int order)
    {
        this.order = order;
    }

    public Rect getRect()
    {
        return rect;
    }

    public void setRect(Rect rect)
    {
        this.rect = rect;
    }

    public boolean isEnabled()
    {
        return enabled;
    }

    public void setEnabled(boolean enabled)
    {
        this.enabled = enabled;
    }

    @JsonIgnore
    public String getEnabledAsText()
    {
        return enabled ? "checked" : "";
    }
}
