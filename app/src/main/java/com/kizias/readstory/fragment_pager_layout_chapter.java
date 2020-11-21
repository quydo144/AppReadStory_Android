package com.kizias.readstory;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.kizias.readstory.Adapter.PageAdapter;
import com.kizias.readstory.Adapter.PageChapterAdapter;
import com.kizias.readstory.Model.History;
import com.kizias.readstory.Model.MessageHistory;
import com.kizias.readstory.Retrofit2.APIUtils;
import com.kizias.readstory.Retrofit2.DataClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class fragment_pager_layout_chapter extends Fragment {

    SharedPreferences preferences, preferencesUser, preferencesHistory;
    View view;
    ViewPager viewpager_chapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_pager_layout_chapter, container, false);
        preferences = getContext().getSharedPreferences("chapter_story", Context.MODE_PRIVATE);
        preferencesUser = getContext().getSharedPreferences("user", Context.MODE_PRIVATE);
        int count = preferences.getInt("chapter_count", 0);
        viewpager_chapter = view.findViewById(R.id.viewpager_chapter);
        PageChapterAdapter adapter = new PageChapterAdapter(getChildFragmentManager(), count);
        setNumberChapterStory();
        viewpager_chapter.setAdapter(adapter);

        return view;
    }

    protected void setNumberChapterStory(){
        String uid_user = preferencesUser.getString("user_id", "");
        if (uid_user.length() != 0){
            String id_story = preferences.getString("story_id", "");
            History history = new History();
            history.setUidUser(uid_user);
            history.setIdStory(id_story);
            DataClient dataClient = APIUtils.getDataHistory();
            Call<MessageHistory> call = dataClient.GetDetailHistory(history);
            call.enqueue(new Callback<MessageHistory>() {
                @Override
                public void onResponse(Call<MessageHistory> call, Response<MessageHistory> response) {
                    if (response.isSuccessful()){
                        if (response.body().getSuccess() == 1){
                            viewpager_chapter.setCurrentItem(response.body().getData().getChapter() - 1);
                        }
                    }
                }

                @Override
                public void onFailure(Call<MessageHistory> call, Throwable t) {

                }
            });
        }
    }
}