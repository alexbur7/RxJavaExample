package com.project.rxjavaexample;

import androidx.annotation.NonNull;

public class User {
    private String urlImage;
    private String subtitle;
    private String text;

    public User(){}
    public User(String urlImage, String subtitle, String text){
        this.urlImage = urlImage;
        this.subtitle = subtitle;
        this.text = text;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @NonNull
    @Override
    public String toString() {
        return "Subtitle: "+getSubtitle()+" text: "+ getText()+" url: "+getUrlImage();
    }
}
