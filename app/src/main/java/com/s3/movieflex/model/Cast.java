package com.s3.movieflex.model;

import java.io.Serializable;

public class Cast implements Serializable {
    String name;
    String imgLink;

    public Cast(String name, String imgLink) {
        this.name = name;
        this.imgLink = imgLink;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgLink() {
        return imgLink;
    }

    public void setImgLink(String imgLink) {
        this.imgLink = imgLink;
    }
}
