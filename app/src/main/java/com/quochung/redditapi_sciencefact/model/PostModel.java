package com.quochung.redditapi_sciencefact.model;

public class PostModel {
    private String title , author, thumbnail, avatar;

    public PostModel(String title , String author, String thumbnail, String avatar){
        this.title = title;
        this.author = author;
        this.thumbnail = thumbnail;
        this.avatar = avatar;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getAuthor() {
        return author;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
