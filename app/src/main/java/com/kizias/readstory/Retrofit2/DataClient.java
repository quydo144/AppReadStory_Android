package com.kizias.readstory.Retrofit2;

import com.kizias.readstory.Model.History;
import com.kizias.readstory.Model.MessageHistory;
import com.kizias.readstory.Model.MessageStory;
import com.kizias.readstory.Model.MessageStoryChapter;
import com.kizias.readstory.Model.MessageStoryDetail;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface DataClient {

    @GET("stories/list")
    Call<MessageStory> GetDanhSachTruyen(
            @Query("limit") Integer limit,
            @Query("offset") Integer offset,
            @Query("sort") String sort
    );

    @GET("stories/list")
    Call<MessageStory> GetDanhSachTruyenFull(
            @Query("full") Integer full,
            @Query("limit") Integer limit,
            @Query("offset") Integer offset,
            @Query("sort") String sort
    );

    @GET("stories")
    Call<MessageStoryDetail> GetStoryDetail(@Query("id") String id);

    @GET("chapters/detail")
    Call<MessageStoryChapter> GetStoryChapter(
            @Query("number") Integer number,
            @Query("story_id") String story_id
    );

    @GET("stories/list")
    Call<MessageStory> SearchDanhSachTruyen(
            @Query("keyword") String keyword,
            @Query("limit") Integer offset,
            @Query("offset") Integer sort
    );

    @POST("/")
    Call<MessageHistory> AddHistoryStory(@Body History history);

    @POST("/detail")
    Call<MessageHistory> GetDetailHistory(@Body History history);

    @PATCH("/")
    Call<MessageHistory> UpdateHistory(@Body History history);
}
