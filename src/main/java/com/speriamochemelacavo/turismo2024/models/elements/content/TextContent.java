package com.speriamochemelacavo.turismo2024.models.elements.content;

import com.speriamochemelacavo.turismo2024.models.elements.category.ContentType;

public class TextContent extends Content {

    private String text;

    public TextContent(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public ContentType getContentType() {
        return ContentType.TEXT;
    }
}
