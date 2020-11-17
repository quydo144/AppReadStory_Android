package com.kizias.readstory.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class StoryDetail {
    @SerializedName("_id")
    @Expose
    private Id id;
    @SerializedName("crawl_url")
    @Expose
    private String crawlUrl;
    @SerializedName("created")
    @Expose
    private Integer created;
    @SerializedName("updated")
    @Expose
    private Integer updated;
    @SerializedName("author")
    @Expose
    private String author;
    @SerializedName("cover")
    @Expose
    private String cover;
    @SerializedName("desc")
    @Expose
    private String desc;
    @SerializedName("first_chapter_url")
    @Expose
    private String firstChapterUrl;
    @SerializedName("full")
    @Expose
    private Boolean full;
    @SerializedName("genre")
    @Expose
    private ArrayList<String> genre;
    @SerializedName("source")
    @Expose
    private String source;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("chapter_count")
    @Expose
    private Integer chapterCount;
    @SerializedName("publish")
    @Expose
    private Boolean publish;
    @SerializedName("view_count")
    @Expose
    private Integer viewCount;
    @SerializedName("like_count")
    @Expose
    private Integer likeCount;

    public Id getId() {
        return id;
    }

    public void setId(Id id) {
        this.id = id;
    }

    public String getCrawlUrl() {
        return crawlUrl;
    }

    public void setCrawlUrl(String crawlUrl) {
        this.crawlUrl = crawlUrl;
    }

    public Integer getCreated() {
        return created;
    }

    public void setCreated(Integer created) {
        this.created = created;
    }

    public Integer getUpdated() {
        return updated;
    }

    public void setUpdated(Integer updated) {
        this.updated = updated;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getFirstChapterUrl() {
        return firstChapterUrl;
    }

    public void setFirstChapterUrl(String firstChapterUrl) {
        this.firstChapterUrl = firstChapterUrl;
    }

    public Boolean getFull() {
        return full;
    }

    public void setFull(Boolean full) {
        this.full = full;
    }

    public ArrayList<String> getGenre() {
        return genre;
    }

    public void setGenre(ArrayList<String> genre) {
        this.genre = genre;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getChapterCount() {
        return chapterCount;
    }

    public void setChapterCount(Integer chapterCount) {
        this.chapterCount = chapterCount;
    }

    public Boolean getPublish() {
        return publish;
    }

    public void setPublish(Boolean publish) {
        this.publish = publish;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }
}
