package com.kizias.readstory;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseAuth;

import static android.content.Context.ACTIVITY_SERVICE;

public class fragmentf_profile extends Fragment {

    View view;
    ImageView imageview_icon_profile, setting;
    TextView textview_name_profile, historyStory;
    SharedPreferences preferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_profile, container, false);
        Init();
        historyStory_Click();
        Setting_Click();

        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(getContext());
        if (account != null){
            Glide.with(getContext()).load(account.getPhotoUrl()).into(imageview_icon_profile);
            textview_name_profile.setText(account.getDisplayName());
        }

        return view;
    }

    protected void Init(){
        preferences = getContext().getSharedPreferences("user", Context.MODE_PRIVATE);
        imageview_icon_profile = (ImageView) view.findViewById(R.id.imageview_icon_profile);
        textview_name_profile = (TextView) view.findViewById(R.id.textview_name_profile);
        setting = (ImageButton) view.findViewById(R.id.setting);
        historyStory = (TextView) view.findViewById(R.id.historyStory);
    }

    protected void Setting_Click(){
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(getContext(), setting);
                popup.inflate(R.menu.menu_setting);
                popup.show();
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.logout:
                                Logout();
                                break;
                        }
                        return false;
                    }
                });
            }
        });
    }

    protected void historyStory_Click(){
        historyStory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), activity_history_story.class);
                startActivity(intent);
            }
        });
    }

    protected void Logout(){
        FirebaseAuth.getInstance().signOut();
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.apply();
        Intent intent = new Intent(getContext(), activity_main.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        Toast.makeText(getContext(), "Đăng xuất thành công", Toast.LENGTH_SHORT).show();
//        if (Build.VERSION_CODES.KITKAT <= Build.VERSION.SDK_INT) {
//            ((ActivityManager) getContext().getSystemService(ACTIVITY_SERVICE)).clearApplicationUserData();
//            return;
//        }
    }
}