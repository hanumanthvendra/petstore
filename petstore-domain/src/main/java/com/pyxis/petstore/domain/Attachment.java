package com.pyxis.petstore.domain;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class Attachment implements Serializable {

    private String fileName;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String name) {
        this.fileName = name;
    }

    @Override
    public String toString() {
        return fileName;
    }
}