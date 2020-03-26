package com.goodwill.apainterviewapp.models;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.io.Serializable;

public class Article implements Serializable {

    private String title,author,publishedDate, captionUrl,contentUrl;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getCaptionUrl() {
        return captionUrl;
    }

    public void setCaptionUrl(String captionUrl) {
        this.captionUrl = captionUrl;
    }

    public String getContentUrl() {
        return contentUrl;
    }

    public void setContentUrl(String contentUrl) {
        this.contentUrl = contentUrl;
    }

    @BindingAdapter("articleImage")
    public static void loadImage(ImageView drinkImage, String imageUrl){
        Glide.with(drinkImage.getContext())
                .load(imageUrl).apply(new RequestOptions())
                .apply(RequestOptions.circleCropTransform())
                .into(drinkImage);
    }
}
