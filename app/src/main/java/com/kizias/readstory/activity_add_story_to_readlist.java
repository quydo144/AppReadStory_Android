package com.kizias.readstory;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Toast;

import com.kizias.readstory.Model.History;
import com.kizias.readstory.Model.MessageHistory;
import com.kizias.readstory.Retrofit2.APIUtils;
import com.kizias.readstory.Retrofit2.DataClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class activity_add_story_to_readlist extends AppCompatActivity {

    Fragment fragment;
    SharedPreferences preferences, preferencesUser, preferencesHistory;
    String id = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_story_to_readlist);
        preferences = getSharedPreferences("chapter_story", MODE_PRIVATE);
        preferencesUser = getSharedPreferences("user", Context.MODE_PRIVATE);
        preferencesHistory = getSharedPreferences("history", Context.MODE_PRIVATE);
        id = getIntent().getStringExtra("id_story");
        fragment = new fragment_pager_layout_chapter();
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("story_id", id);
        editor.apply();;
        getSupportFragmentManager().beginTransaction().replace(R.id.add_story_to_readlist_frame_layout, fragment).commit();
    }

    @Override
    public void onBackPressed() {
        PostHistory();
        super.onBackPressed();
    }


    private void PostHistory(){
        String uid_user = preferencesUser.getString("user_id", "");
        if (uid_user.length() != 0){
            String id_story = preferences.getString("story_id", "");
            int chapter = preferencesHistory.getInt("chapter", 0);
            String location = preferencesHistory.getString("location", "");
            History history = new History();
            history.setUidUser(uid_user);
            history.setIdStory(id_story);
            history.setChapter(chapter);
            history.setLocation(location);
            DataClient dataClient = APIUtils.getDataHistory();
            Call<MessageHistory> callDetail = dataClient.GetDetailHistory(history);
            callDetail.enqueue(new Callback<MessageHistory>() {
                @Override
                public void onResponse(Call<MessageHistory> call, Response<MessageHistory> response) {
                    if (response.isSuccessful()){
                        if (response.body().getSuccess() == 1){
                            Update();
                        }
                        else Add();
                    }
                }

                @Override
                public void onFailure(Call<MessageHistory> call, Throwable t) {

                }
            });
        }
    }

    protected void Update(){
        DataClient dataClient = APIUtils.getDataHistory();
        String id_story = preferences.getString("story_id", "");
        int chapter = preferencesHistory.getInt("chapter", 0) - 1;
        String uid_user = preferencesUser.getString("user_id", "");
        String location = preferencesHistory.getString("location", "");
        History history = new History();
        history.setUidUser(uid_user);
        history.setIdStory(id_story);
        history.setChapter(chapter);
        history.setLocation(location);
        Call<MessageHistory> callAdd = dataClient.UpdateHistory(history);
        callAdd.enqueue(new Callback<MessageHistory>() {
            @Override
            public void onResponse(Call<MessageHistory> call, Response<MessageHistory> response) {
                if (response.isSuccessful()){

                }
            }

            @Override
            public void onFailure(Call<MessageHistory> call, Throwable t) {

            }
        });
    }

    protected void Add(){
        DataClient dataClient = APIUtils.getDataHistory();
        String id_story = preferences.getString("story_id", "");
        int chapter = preferencesHistory.getInt("chapter", 0);
        String uid_user = preferencesUser.getString("user_id", "");
        String location = preferencesHistory.getString("location", "");
        History history = new History();
        history.setUidUser(uid_user);
        history.setIdStory(id_story);
        history.setChapter(chapter);
        history.setLocation(location);
        Call<MessageHistory> callAdd = dataClient.AddHistoryStory(history);
        callAdd.enqueue(new Callback<MessageHistory>() {
            @Override
            public void onResponse(Call<MessageHistory> call, Response<MessageHistory> response) {

            }

            @Override
            public void onFailure(Call<MessageHistory> call, Throwable t) {

            }
        });
    }
}