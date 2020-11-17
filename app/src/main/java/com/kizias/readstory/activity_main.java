package com.kizias.readstory;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class activity_main extends AppCompatActivity {

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
//                case R.id.navigation_search:
//                    break;
//                case R.id.navigation_history:
//                    break;
//                case R.id.navigation_person:
//                    break;
                default:
                    return false;
            }
            return true;
        }
    };
}