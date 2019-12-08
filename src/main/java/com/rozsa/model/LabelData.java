package com.rozsa.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Objects;

public class LabelData
{
    private String text;

    private String font;

    private int style;

    private int size;

    private ColorData color;

    private Rect rect;

    private int verticalAlignment;

    private int horizontalAlignment;

    public LabelData() {}

    public LabelData(int size, Rect rect, int verAlign, int horAlign, String text)
    {
        this.text = text;
        font = "Serif";
        style = 0;
        this.size = size;
        color = new ColorData(255, 255, 255);
        this.rect = rect;
        verticalAlignment = verAlign;
        horizontalAlignment = horAlign;
    }

    public int getHorizontalAlignment() {
        return horizontalAlignment;
    }

    public void setHorizontalAlignment(int horizontalAlignment) {
        this.horizontalAlignment = horizontalAlignment;
    }

    public int getVerticalAlignment() {
        return verticalAlignment;
    }

    public void setVerticalAlignment(int verticalAlignment) {
        this.verticalAlignment = verticalAlignment;
    }

    public String getText()
    {
        return text;
    }

    public void setText(String text)
    {
        this.text = text;
    }

    public String getFont()
    {
        return font;
    }

    public void setFont(String font)
    {
        this.font = font;
    }

    public int getStyle()
    {
        return style;
    }

    public void setStyle(int style)
    {
        this.style = style;
    }

    public int getSize()
    {
        return size;
    }

    public void setSize(int size)
    {
        this.size = size;
    }

    public ColorData getColor()
    {
        return color;
    }

    public void setColor(ColorData color)
    {
        this.color = color;
    }

    public Rect getRect()
    {
        return rect;
    }

    public void setRect(Rect rect)
    {
        this.rect = rect;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LabelData labelData = (LabelData) o;
        return style == labelData.style &&
                size == labelData.size &&
                verticalAlignment == labelData.verticalAlignment &&
                horizontalAlignment == labelData.horizontalAlignment &&
                text.equals(labelData.text) &&
                font.equals(labelData.font) &&
                color.equals(labelData.color) &&
                rect.equals(labelData.rect);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(text, font, style, size, color, rect, verticalAlignment, horizontalAlignment);
    }

    @JsonIgnore
    public String getColorAsHex() {
        String hexValue = "#";
        hexValue += Integer.toHexString(color.getR());
        hexValue += Integer.toHexString(color.getG());
        hexValue += Integer.toHexString(color.getB());
        return hexValue;
    }
}
