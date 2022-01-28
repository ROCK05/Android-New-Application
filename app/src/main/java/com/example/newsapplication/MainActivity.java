package com.example.newsapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;
    TabItem home, health, enter, business, science, sports, technology, world;
    PagerAdapter pagerAdapter;

    ViewPager viewPager;

    String api = "fb7ad87d4c0a4e51af23b8caa9ea8248";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.countryToolbar);
        toolbar.setTitle("NewsApp");
        setSupportActionBar(toolbar);

        tabLayout = findViewById(R.id.category);
        final int[] ICONS = new int[]{
                R.drawable.topnews,
                R.drawable.health,
                R.drawable.entertainment,
                R.drawable.science,
                R.drawable.business,
                R.drawable.sports,
                R.drawable.technology,
                R.drawable.world
                };

        home = findViewById(R.id.home);
        health = findViewById(R.id.health);
        enter = findViewById(R.id.entertainment);
        science = findViewById(R.id.science);
        sports = findViewById(R.id.sports);
        technology = findViewById(R.id.technology);
        business = findViewById(R.id.business);
        world = findViewById(R.id.world);
        viewPager = findViewById(R.id.fragmentContainer);

        pagerAdapter = new MyAdapter(getSupportFragmentManager(), 8);
        viewPager.setAdapter(pagerAdapter);

        tabLayout.getTabAt(0).setIcon(ICONS[0]);
        tabLayout.getTabAt(1).setIcon(ICONS[1]);
        tabLayout.getTabAt(2).setIcon(ICONS[2]);
        tabLayout.getTabAt(3).setIcon(ICONS[3]);
        tabLayout.getTabAt(4).setIcon(ICONS[4]);
        tabLayout.getTabAt(5).setIcon(ICONS[5]);
        tabLayout.getTabAt(6).setIcon(ICONS[6]);
        tabLayout.getTabAt(7).setIcon(ICONS[7]);

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

        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Search in a single word only");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
               Intent intent = new Intent(getApplicationContext(), searchNewsView.class);
               intent.putExtra("QueryText", query);
               startActivity(intent);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
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