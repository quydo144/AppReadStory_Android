package com.kizias.readstory;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseUser;

public class activity_main extends AppCompatActivity {

    SharedPreferences preferences;
    Fragment selectedFragment;
    BottomNavigationView navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        selectedFragment = new fragment_main();
        getSupportFragmentManager().beginTransaction().replace(R.id.framelayout_main, selectedFragment).commit();
        Init();
    }

    protected void Init() {
        preferences = getSharedPreferences("user", MODE_PRIVATE);
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(navListener);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_feature:
                    Fragment fragment = new fragment_main();
                    getSupportFragmentManager().beginTransaction().replace(R.id.framelayout_main, fragment).commit();
                    break;
//                case R.id.navigation_category:
//                    break;
                case R.id.navigation_search:
                    selectedFragment = new fragment_search();
                    getSupportFragmentManager().beginTransaction().replace(R.id.framelayout_main, selectedFragment).commit();
                    break;
                case R.id.navigation_person:
                    String uid = preferences.getString("user_id", "");
                    if (uid.length() != 0){
                        selectedFragment = new fragmentf_profile();
                        getSupportFragmentManager().beginTransaction().replace(R.id.framelayout_main, selectedFragment).commit();
                    }
                    else {
                        Intent intent = new Intent(getApplicationContext(), activity_login.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }
                    break;
                default:
                    return false;
            }
            return true;
        }
    };
}