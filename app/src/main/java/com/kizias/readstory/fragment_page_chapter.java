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

    ScrollView scrollText;
    SharedPreferences preferences;
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
        scrollText = view.findViewById(R.id.scrollText);
        LoadData();
        scrollText.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                int[] viewLocation = new int[2];
                viewLocation[0] = scrollX;
                viewLocation[1] = scrollY;
                list.add(viewLocation);
            }
        });
        return view;
    }

    private void ScrollView() {

    }

    protected void LoadData() {
        progressBarTop.setVisibility(View.VISIBLE);
        DataClient dataClient = APIUtils.getData();
        int count = getArguments().getInt("number");
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