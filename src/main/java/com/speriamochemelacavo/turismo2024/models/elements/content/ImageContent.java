package com.speriamochemelacavo.turismo2024.models.elements.content;

import com.speriamochemelacavo.turismo2024.models.elements.category.ContentType;

import java.io.File;

public class ImageContent extends Content {
    private File imageFile;

    public ImageContent(File imageFile) {
        this.imageFile = imageFile;
    }

    public File getImageFile() {
        return imageFile;
    }

    public void setImageFile(File imageFile) {
        this.imageFile = imageFile;
    }

    public ContentType getContentType() {
        super.setContentType(ContentType.IMAGE);
        return super.getContentType();
    }
}
