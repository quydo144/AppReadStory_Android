package com.kizias.readstory.Retrofit2;

import com.kizias.readstory.Model.MessageStory;
import com.kizias.readstory.Model.MessageStoryDetail;

import retrofit2.Call;
import retrofit2.http.GET;
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
}
