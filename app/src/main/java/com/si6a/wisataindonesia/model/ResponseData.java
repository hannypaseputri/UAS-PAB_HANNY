package com.si6a.wisataindonesia.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseData {
    @SerializedName("message") private String message;
    @SerializedName("user") private UserData userData;
    @SerializedName("travels") private List<TravelData> travelDataList;

    public String getMessage() {
        return message;
    }

    public UserData getUserData() {
        return userData;
    }

    public List<TravelData> getTravelDataList() {
        return travelDataList;
    }
}
