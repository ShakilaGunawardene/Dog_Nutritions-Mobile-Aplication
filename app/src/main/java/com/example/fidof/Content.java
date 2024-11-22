package com.example.fidof;

public class Content {
    String id, imageURL, contentDescription, contentName;

    public String getContentDescription() {
        return contentDescription;
    }

    public void setContentDescription(String contentDescription) {
        this.contentDescription = contentDescription;
    }

    public String getContentName() {
        return contentName;
    }

    public void setContentName(String contentName) {
        this.contentName = contentName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public Content(String contentDescription, String contentName, String id, String imageURL) {
        this.contentDescription = contentDescription;
        this.contentName = contentName;
        this.id = id;
        this.imageURL = imageURL;
    }

    public Content() {
    }
    public class content{

    }
}
