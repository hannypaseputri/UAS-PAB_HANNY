package com.si6a.wisataindonesia.model;

import com.google.gson.annotations.SerializedName;

public class TravelData {
    @SerializedName("_id") private String id;
    @SerializedName("title") private String title;
    @SerializedName("description") private String description;
    @SerializedName("image") private String image;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
