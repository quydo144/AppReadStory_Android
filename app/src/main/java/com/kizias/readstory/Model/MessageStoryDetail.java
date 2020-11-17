package com.kizias.readstory.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MessageStoryDetail {
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("data")
    @Expose
    private StoryDetail data;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public StoryDetail getData() {
        return data;
    }

    public void setData(StoryDetail data) {
        this.data = data;
    }

}
