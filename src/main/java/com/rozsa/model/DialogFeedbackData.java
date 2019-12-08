package com.rozsa.model;

public class DialogFeedbackData {
    private static final String htmlTemplate = "<html><body></body></html>";

    private int id;

    private LabelData title;

    private LabelData text;

    public DialogFeedbackData() {
    }

    public DialogFeedbackData(int id) {
        this.id = id;
        title = new LabelData(22, new Rect(12, 4, 920, 32), 2, 0, htmlTemplate);
        text = new LabelData(20, new Rect(16, 32, 920, 64), 2, 1, htmlTemplate);
    }

    public LabelData getTitle() {
        return title;
    }

    public void setTitle(LabelData title) {
        this.title = title;
    }

    public LabelData getText() {
        return text;
    }

    public void setText(LabelData text) {
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
