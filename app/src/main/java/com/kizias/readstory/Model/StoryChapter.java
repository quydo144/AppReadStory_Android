package com.kizias.readstory.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StoryChapter {
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Id getStoryId() {
        return storyId;
    }

    public void setStoryId(Id storyId) {
        this.storyId = storyId;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getCrawlUrl() {
        return crawlUrl;
    }

    public void setCrawlUrl(String crawlUrl) {
        this.crawlUrl = crawlUrl;
    }

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("content")
    @Expose
    private String content;
    @SerializedName("story_id")
    @Expose
    private Id storyId;
    @SerializedName("number")
    @Expose
    private Integer number;
    @SerializedName("crawl_url")
    @Expose
    private String crawlUrl;
}
