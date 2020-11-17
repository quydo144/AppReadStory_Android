package com.kizias.readstory;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.adroitandroid.chipcloud.ChipCloud;
import com.bumptech.glide.Glide;
import com.kizias.readstory.Model.MessageStoryDetail;
import com.kizias.readstory.Model.Story;
import com.kizias.readstory.Model.StoryDetail;
import com.kizias.readstory.Retrofit2.APIUtils;
import com.kizias.readstory.Retrofit2.DataClient;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class activity_story_detail extends AppCompatActivity {

    CircleImageView imageview_story_detail_cover;
    TextView textview_story_detail_name, textview_story_detail_chapter_count, textview_story_detail_status,
            textview_story_detail_description,textview_story_detail_author, textview_read_story_detail;
    ImageButton detail_close_btn;
    ImageView imageview_story_detail_full;
    SwipeRefreshLayout swipe_story_detail;
    ChipCloud chipCloud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_detail);
        Init();
        LoadData();
        Close();
        SwipeRefreshLayout();
        ReadStory_Click();
    }

    private void Init(){
        detail_close_btn = (ImageButton) findViewById(R.id.detail_close_btn);
        textview_story_detail_name = (TextView) findViewById(R.id.textview_story_detail_name);
        imageview_story_detail_cover = (CircleImageView) findViewById(R.id.imageview_story_detail_cover);
        imageview_story_detail_full = (ImageView) findViewById(R.id.imageview_story_detail_full);
        textview_story_detail_chapter_count = (TextView) findViewById(R.id.textview_story_detail_chapter_count);
        textview_story_detail_status = (TextView) findViewById(R.id.textview_story_detail_status);
        textview_story_detail_description = (TextView) findViewById(R.id.textview_story_detail_description);
        swipe_story_detail = (SwipeRefreshLayout) findViewById(R.id.swipe_story_detail);
        textview_story_detail_author = (TextView) findViewById(R.id.textview_story_detail_author);
        chipCloud = (ChipCloud) findViewById(R.id.chip_cloud_story_tag);
        textview_read_story_detail = findViewById(R.id.textview_read_story_detail);
    }

    protected void SwipeRefreshLayout() {
        swipe_story_detail.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipe_story_detail.setRefreshing(false);
            }
        });
        swipe_story_detail.setColorSchemeResources(
                R.color.colorPrimary,
                R.color.switch_thumb_normal_material_dark,
                R.color.primary_dark_material_light,
                R.color.error_color_material_dark);
    }

    protected void ReadStory_Click(){
        textview_read_story_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getIntent().hasExtra("id_story")){
                    String id = getIntent().getStringExtra("id_story");
                    Intent intent = new Intent(getApplicationContext(), activity_add_story_to_readlist.class);
                    intent.putExtra("id_story", id);
                    startActivity(intent);
                }
            }
        });
    }

    private void LoadData(){
        if (getIntent().hasExtra("id_story")){
            String id = getIntent().getStringExtra("id_story");
            DataClient dataClient = APIUtils.getData();
            Call<MessageStoryDetail> call = dataClient.GetStoryDetail(id);
            call.enqueue(new Callback<MessageStoryDetail>() {
                @Override
                public void onResponse(Call<MessageStoryDetail> call, Response<MessageStoryDetail> response) {
                    if (response.body() != null) {
                        if (response.body().getStatus()) {
                            RenderData(response.body().getData());
                        }
                    }
                }

                @Override
                public void onFailure(Call<MessageStoryDetail> call, Throwable t) {

                }
            });
        }
    }

    protected void ChipCloud(ArrayList<String> lst){
        for (String gene : lst){
            chipCloud.addChip(gene);
        }
    }

    protected void RenderData(StoryDetail storyDetail){
        if (storyDetail.getFull()){
            textview_story_detail_name.setText(storyDetail.getTitle());
            Glide.with(getApplicationContext()).load(storyDetail.getCover()).into(imageview_story_detail_cover);
            textview_story_detail_chapter_count.setText(storyDetail.getChapterCount() + "" + " chương");
            textview_story_detail_status.setText("[FULL]");
            textview_story_detail_description.setText(android.text.Html.fromHtml(storyDetail.getDesc()));
            textview_story_detail_author.setText(storyDetail.getAuthor());
            ChipCloud(storyDetail.getGenre());
        }
        else {
            textview_story_detail_name.setText(storyDetail.getTitle());
            Glide.with(getApplicationContext()).load(storyDetail.getCover()).into(imageview_story_detail_cover);
            imageview_story_detail_full.setVisibility(View.GONE);
            textview_story_detail_chapter_count.setText(storyDetail.getChapterCount() + "" + " chương");
            textview_story_detail_status.setText("Đang ra");
            textview_story_detail_description.setText(android.text.Html.fromHtml(storyDetail.getDesc()));
            textview_story_detail_author.setText(storyDetail.getAuthor());
            ChipCloud(storyDetail.getGenre());
        }
    }

    private void Close(){
        detail_close_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}