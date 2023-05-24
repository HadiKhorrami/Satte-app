package com.example.satte.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.example.satte.Fragment.Dashboard;
import com.example.satte.Fragment.Exploitation;
import com.example.satte.Fragment.Profile;
import com.example.satte.Fragment.Review;
import com.example.satte.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        load_SmoothBottomBar();
        loadDefaultFragment();

    }
    private void load_SmoothBottomBar() {
        bottomNavigationView = findViewById(R.id.bottomnavigation_main);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                Fragment selectedFragment = null;
                switch (menuItem.getItemId()) {
                    case R.id.itemdashboard:
                        selectedFragment = Dashboard.newInstance();
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_placeholder, selectedFragment).commit();
                        return true;
                    case R.id.itemreview:
                        selectedFragment = Review.newInstance();
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_placeholder, selectedFragment).commit();
                        return true;
                    case R.id.itemexploitation:
                        selectedFragment = Exploitation.newInstance();
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_placeholder, selectedFragment).commit();
                        return true;
                    case R.id.itemprofile:
                        selectedFragment = Profile.newInstance();
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_placeholder, selectedFragment).commit();
                        return true;

                    default:
                        return true;
                }
            }
        });
    }
    private void loadDefaultFragment() {
        Fragment fragment = Dashboard.newInstance();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_placeholder, fragment).commit();
    }
    @Override
    public void onBackPressed() {
        finish();
    }

}