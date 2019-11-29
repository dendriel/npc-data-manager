package com.rozsa.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.awt.*;
import java.util.Objects;

public class ColorData
{
    private int r;

    private int g;

    private int b;

    public ColorData() {}

    public ColorData(int r, int g, int b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public int getR()
    {
        return r;
    }

    public void setR(int r)
    {
        this.r = r;
    }

    public int getG()
    {
        return g;
    }

    public void setG(int g)
    {
        this.g = g;
    }

    public int getB()
    {
        return b;
    }

    public void setB(int b)
    {
        this.b = b;
    }

    @JsonIgnore
    public Color getColor()
    {
        return new Color(r, g, b);
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ColorData colorData = (ColorData) o;
        return r == colorData.r &&
                g == colorData.g &&
                b == colorData.b;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(r, g, b);
    }
}
