package com.mygoals;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import lombok.Data;

@Data
public class News  implements Serializable {
    @SerializedName("strMeal")
private String title;
    @SerializedName("strInstructions")
private String description;
    @SerializedName("strSource")
private String link;

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @SerializedName("strMealThumb")
    private String picture;
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public News(String title, String description, String link,String picture) {
        this.title = title;
        this.description = description;
        this.link = link;
        this.picture=picture;
    }
}
