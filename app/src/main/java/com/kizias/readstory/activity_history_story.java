package com.kizias.readstory;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.kizias.readstory.Adapter.MyAdapter;
import com.kizias.readstory.Model.History;
import com.kizias.readstory.Model.MessageHistory;
import com.kizias.readstory.Model.MessageStory;
import com.kizias.readstory.Model.MessageStoryDetail;
import com.kizias.readstory.Model.Story;
import com.kizias.readstory.Model.StoryDetail;
import com.kizias.readstory.Retrofit2.APIUtils;
import com.kizias.readstory.Retrofit2.DataClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class activity_history_story extends AppCompatActivity {

    SharedPreferences preferencesUser;
    ProgressBar progressBarTop;
    MyAdapter adapter;
    ArrayList<Story> lstStory;
    ArrayList<History> lstHistory;
    SwipeRefreshLayout swipeRefreshLayout;
    ImageButton delete_history, detail_close_btn;
    RecyclerView recyclerview_story;
    LinearLayoutManager layoutManager;
    Boolean isScrolling = false;
    int currentItem, totalItem, scrollOutItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_story);
        lstHistory = new ArrayList<>();
        lstStory = new ArrayList<>();
        Init();
        LoadData();
        Close();
        delete_history_Click();
        SwipeRefreshLayout();
    }

    protected void Init() {
        detail_close_btn = (ImageButton) findViewById(R.id.detail_close_btn);
        delete_history = (ImageButton) findViewById(R.id.delete_history);
        preferencesUser = getSharedPreferences("user", Context.MODE_PRIVATE);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_story_tab);
        recyclerview_story = (RecyclerView) findViewById(R.id.recyclerview_story);
        progressBarTop = (ProgressBar) findViewById(R.id.progressBarTop);
    }

    protected void Close(){
        detail_close_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    protected void delete_history_Click(){
        delete_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(activity_history_story.this);
                dialog.setTitle("Hỏi đáp");
                dialog.setMessage("Bạn có muốn xoá hay không?");
                dialog.setCancelable(true);
                dialog.setPositiveButton("Xoá", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String uid_user = preferencesUser.getString("user_id", "").trim();
                        DataClient dataClient = APIUtils.getDataHistory();
                        History history = new History();
                        history.setUidUser(uid_user);
                        Call<MessageHistory> call = dataClient.DeleteHistory(history);
                        call.enqueue(new Callback<MessageHistory>() {
                            @Override
                            public void onResponse(Call<MessageHistory> call, Response<MessageHistory> response) {
                                if (response.isSuccessful())
                                    if (response.body().getSuccess() == 1) {
                                        LoadData();
                                    }
                            }

                            @Override
                            public void onFailure(Call<MessageHistory> call, Throwable t) {

                            }
                        });
                    }
                });

                dialog.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog alertDialog = dialog.create();
                alertDialog.show();
            }
        });
    }

    protected void LoadDataHistory() {
        DataClient dataClient = APIUtils.getData();
        for (History h : lstHistory) {
            Call<MessageStoryDetail> call = dataClient.GetStoryDetail(h.getIdStory());
            call.enqueue(new Callback<MessageStoryDetail>() {
                @Override
                public void onResponse(Call<MessageStoryDetail> call, Response<MessageStoryDetail> response) {
                    if (response.isSuccessful()) {
                        if (response.body().getStatus()) {
                            StoryDetail detail = response.body().getData();
                            Story story = new Story();
                            story.setCover(detail.getCover());
                            story.setTitle(detail.getTitle());
                            story.setId(detail.getId());
                            lstStory.add(story);
                            ShowDanhSach();
                            adapter.notifyDataSetChanged();
                        }
                    }
                }

                @Override
                public void onFailure(Call<MessageStoryDetail> call, Throwable t) {

                }
            });
        }

    }

    protected void ShowDanhSach() {
        layoutManager = new LinearLayoutManager(getApplicationContext());
        adapter = new MyAdapter(getApplicationContext(), lstStory);
        recyclerview_story.setAdapter(adapter);
        recyclerview_story.setHasFixedSize(true);
        recyclerview_story.setLayoutManager(layoutManager);
    }

    protected void LoadData() {
        lstStory.clear();
        lstHistory.clear();
        progressBarTop.setVisibility(View.VISIBLE);
        String uid_user = preferencesUser.getString("user_id", "").trim();
        DataClient dataClient = APIUtils.getDataHistory();
        Call<MessageHistory> callData = dataClient.GetHistory(uid_user);

        callData.enqueue(new Callback<MessageHistory>() {
            @Override
            public void onResponse(Call<MessageHistory> call, Response<MessageHistory> response) {
                if (response.body() != null) {
                    if (response.body().getSuccess() == 1) {
                        lstHistory.addAll(response.body().getDanhsach());
                        progressBarTop.setVisibility(View.GONE);
                        LoadDataHistory();
                    }
                }
            }

            @Override
            public void onFailure(Call<MessageHistory> call, Throwable t) {
                progressBarTop.setVisibility(View.GONE);
            }
        });
    }

    protected void SwipeRefreshLayout() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        swipeRefreshLayout.setColorSchemeResources(
                R.color.colorPrimary,
                R.color.switch_thumb_normal_material_dark,
                R.color.primary_dark_material_light,
                R.color.error_color_material_dark);
    }
}