package com.rozsa.model;

public class DialogFeedbackData
{
    private int id;

    private LabelData title;

    private LabelData text;

    public DialogFeedbackData() {}

    public DialogFeedbackData(int id) {
        this.id = id;
        title = new LabelData(22);
        text = new LabelData(20);
    }

    public LabelData getTitle()
    {
        return title;
    }

    public void setTitle(LabelData title)
    {
        this.title = title;
    }

    public LabelData getText()
    {
        return text;
    }

    public void setText(LabelData text)
    {
        this.text = text;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }
}
