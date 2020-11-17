package com.kizias.readstory;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.kizias.readstory.Adapter.MyAdapter;
import com.kizias.readstory.Model.Story;
import com.kizias.readstory.Model.MessageStory;
import com.kizias.readstory.Retrofit2.APIUtils;
import com.kizias.readstory.Retrofit2.DataClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class fragment_stories extends Fragment {

    ProgressBar progressBarBottom, progressBarTop;
    MyAdapter adapter;
    View view;
    ArrayList<Story> lstFirst = new ArrayList<>();
    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView recyclerview_story;
    LinearLayoutManager layoutManager;
    Boolean isScrolling = false;
    int currentItem, totalItem, scrollOutItems;
    int i = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_stories, container, false);
        Init();
        i = 0;
        LoadData(i);
        ShowDanhSach();
        SwipeRefreshLayout();

        recyclerview_story.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    isScrolling = true;
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                currentItem = layoutManager.getChildCount();
                totalItem = layoutManager.getItemCount();
                scrollOutItems = layoutManager.findFirstVisibleItemPosition();
                if (isScrolling && (currentItem + scrollOutItems == totalItem)) {
                    isScrolling = false;
                    LoadData(i + 36);
                    i++;
                }
            }
        });
        return view;
    }

    protected void Init() {
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_story_tab);
        recyclerview_story = (RecyclerView) view.findViewById(R.id.recyclerview_story);
        progressBarBottom = (ProgressBar) view.findViewById(R.id.progressBarBottom);
        progressBarTop = (ProgressBar) view.findViewById(R.id.progressBarTop);
    }

    protected void ShowDanhSach() {
        layoutManager = new LinearLayoutManager(view.getContext());
        adapter = new MyAdapter(view.getContext(), lstFirst);
        recyclerview_story.setAdapter(adapter);
        recyclerview_story.setHasFixedSize(true);
        recyclerview_story.setLayoutManager(layoutManager);
    }

    protected void LoadData(int i) {
        if (i != 0 ){
            progressBarBottom.setVisibility(View.VISIBLE);
        }
        else {
            progressBarTop.setVisibility(View.VISIBLE);
        }
        DataClient dataClient = APIUtils.getData();
        Call<MessageStory> call = dataClient.GetDanhSachTruyen(36, i, "created");
        call.enqueue(new Callback<MessageStory>() {
            @Override
            public void onResponse(Call<MessageStory> call, Response<MessageStory> response) {
                if (response.body() != null) {
                    if (response.body().getStatus()) {
                        for (Story data : response.body().getData()) {
                            lstFirst.add(data);
                        }
                        progressBarBottom.setVisibility(View.GONE);
                        if (i == 0){
                            progressBarTop.setVisibility(View.GONE);
                        }
                        adapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(Call<MessageStory> call, Throwable t) {
                Log.e("Giá trị", "0");
            }
        });
    }

    protected void SwipeRefreshLayout() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                lstFirst.clear();
                i = 0;
                LoadData(i);
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