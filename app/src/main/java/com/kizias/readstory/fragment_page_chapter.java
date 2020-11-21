package com.kizias.readstory;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.kizias.readstory.Adapter.PageChapterAdapter;
import com.kizias.readstory.Model.History;
import com.kizias.readstory.Model.MessageHistory;
import com.kizias.readstory.Model.MessageStory;
import com.kizias.readstory.Model.MessageStoryChapter;
import com.kizias.readstory.Model.StoryChapter;
import com.kizias.readstory.Retrofit2.APIUtils;
import com.kizias.readstory.Retrofit2.DataClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class fragment_page_chapter extends Fragment {

    SharedPreferences preferences, preferencesUser, preferencesHistory;
    TextView textview_content_chapter_detail;
    ProgressBar progressBarTop;
    ArrayList<int[]> list = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_page_chapter, container, false);
        progressBarTop = view.findViewById(R.id.progressBarTop);
        textview_content_chapter_detail = view.findViewById(R.id.textview_content_chapter_detail);
        preferences = getContext().getSharedPreferences("chapter_story", Context.MODE_PRIVATE);
        preferencesUser = getContext().getSharedPreferences("user", Context.MODE_PRIVATE);
        preferencesHistory = getContext().getSharedPreferences("history", Context.MODE_PRIVATE);
        LoadData();
        textview_content_chapter_detail.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                int[] viewLocation = new int[2];
                viewLocation[0] = scrollX;
                viewLocation[1] = scrollY;
                list.removeAll(list);
                list.add(viewLocation);
                int locatondX = list.get(list.size() -1 )[0];
                int locatondY = list.get(list.size() -1 )[1];
                String location = (locatondX + "") + " " + (locatondY + "");
                SharedPreferences.Editor editor = preferencesHistory.edit();
                editor.remove("location");
                editor.putString("location", location);
                editor.apply();

            }
        });
        return view;
    }

    protected void LoadData() {
        progressBarTop.setVisibility(View.VISIBLE);
        DataClient dataClient = APIUtils.getData();
        int count = getArguments().getInt("number");
        SharedPreferences.Editor editor = preferencesHistory.edit();
        editor.remove("chapter");
        editor.putInt("chapter", count);
        editor.apply();
        Call<MessageStoryChapter> call = dataClient.GetStoryChapter(count, preferences.getString("story_id", ""));
        call.enqueue(new Callback<MessageStoryChapter>() {
            @Override
            public void onResponse(Call<MessageStoryChapter> call, Response<MessageStoryChapter> response) {
                if (response.body() != null) {
                    if (response.body().getStatus()) {
                        StoryChapter chapter = response.body().getData();
                        String html = "<b>" + chapter.getTitle() + "</b><br><br>" + chapter.getContent() + "<b><p style='text-align: center;'>*** " + count + " ***</p></b>";
                        textview_content_chapter_detail.setText(android.text.Html.fromHtml(html));
                        progressBarTop.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void onFailure(Call<MessageStoryChapter> call, Throwable t) {
                progressBarTop.setVisibility(View.GONE);
            }
        });
    }
}