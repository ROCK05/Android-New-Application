package com.example.newsapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;
    TabItem home, health, enter, business, science, sports, technology, world;
    PagerAdapter pagerAdapter;

    String api = "fb7ad87d4c0a4e51af23b8caa9ea8248";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.countryToolbar);
        toolbar.setTitle("NewsApp");
        setSupportActionBar(toolbar);

        tabLayout = findViewById(R.id.category);

        home = findViewById(R.id.home);
        health = findViewById(R.id.health);
        enter = findViewById(R.id.entertainment);
        science = findViewById(R.id.science);
        sports = findViewById(R.id.sports);
        technology = findViewById(R.id.technology);
        business = findViewById(R.id.business);
        world = findViewById(R.id.world);
        ViewPager viewPager = findViewById(R.id.fragmentContainer);

        pagerAdapter = new MyAdapter(getSupportFragmentManager(), 8);
        viewPager.setAdapter(pagerAdapter);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if (tab.getPosition() == 0 || tab.getPosition() == 1 ||
                        tab.getPosition() == 2 || tab.getPosition() == 3 ||
                        tab.getPosition() == 4 || tab.getPosition() == 5 || tab.getPosition() == 6 ||
                        tab.getPosition() == 7) {
                    pagerAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menuCountry:
                Intent intent = new Intent(this, countryListActivity.class);
                startActivity(intent);
                break;

            case R.id.menuAccount:
                if(accountProfileActivity.isUserLoggedIn)
                {
                    Intent intent2 = new Intent(this,accountProfileActivity.class);
                    startActivity(intent2);
                }
                else
                {
                    Intent intent1 = new Intent(this, loginActivity1.class);
                    startActivity(intent1);
                }

                break;
        }
        return true;
    }
}