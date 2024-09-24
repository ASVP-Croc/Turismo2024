package com.speriamochemelacavo.turismo2024.models.elements.content;

import com.speriamochemelacavo.turismo2024.models.elements.category.ContentType;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class MultimediaMaterialContent extends Content{
    private String type;

    @ElementCollection
    @CollectionTable(name = "fileData", joinColumns = @JoinColumn(name = "materialId"))
    @Column(name = "file")
    private List<String> fileList;

    public MultimediaMaterialContent(List<String> fileList) {
        this.fileList = fileList;
    }

    public List<String> getFileList() {
        return fileList;
    }

    public void setFileList(List<String> fileList) {
        this.fileList = fileList;
    }

    public ContentType getContentType() {
        super.setContentType(ContentType.MULTIMEDIA);
        return super.getContentType();
    }

}
