package com.example.newsapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.newsapplication.fragments.businessFragment;
import com.example.newsapplication.fragments.entertainmentFragment;
import com.example.newsapplication.fragments.healthFragment;
import com.example.newsapplication.fragments.homeFragment;
import com.example.newsapplication.fragments.scienceFragment;
import com.example.newsapplication.fragments.sportsFragment;
import com.example.newsapplication.fragments.technologyFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class countryListActivity extends AppCompatActivity {

    RadioGroup radioCountryGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.countryToolbar);
        toolbar.setTitle("Select Country");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        radioCountryGroup = (RadioGroup) findViewById(R.id.radioGroup);

        ((RadioButton) radioCountryGroup.getChildAt(countries.index)).setChecked(true);

        radioCountryGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

                    @SuppressLint("NonConstantResourceId")
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        String c = null;
                        switch (checkedId)
                        {
                            case R.id.ae:
                                //c = "ae";
                                c = countries.countryCodes[0];
                                countries.index = 0;
                            break;
                            case R.id.ar:
                                countries.index = 1;
                                c = countries.countryCodes[1];
                                break;
                            case R.id.at:
                                countries.index = 2;
                                c = countries.countryCodes[2];
                                break;
                            case R.id.au:
                                countries.index = 3;
                                c = countries.countryCodes[3];
                                break;
                            case R.id.be:
                                countries.index = 4;
                                c = countries.countryCodes[4];
                                break;
                            case R.id.bg:
                                countries.index = 5;
                                c = countries.countryCodes[5];
                                break;
                            case R.id.br:
                                countries.index = 6;
                                c = countries.countryCodes[6];
                                break;
                            case R.id.ca:
                                countries.index = 7;
                                c = countries.countryCodes[7];
                                break;
                            case R.id.ch:
                                countries.index = 8;
                                c = countries.countryCodes[8];
                                break;
                            case R.id.cn:
                                countries.index = 9;
                                c = countries.countryCodes[9];
                                break;
                            case R.id.co:
                                countries.index = 10;
                                c = countries.countryCodes[10];
                                break;
                            case R.id.cu:
                                countries.index = 11;
                                c = countries.countryCodes[11];
                                break;
                            case R.id.cz:
                                countries.index = 12;
                                c = countries.countryCodes[12];
                                break;
                            case R.id.de:
                                countries.index = 13;
                                c = countries.countryCodes[13];
                                break;
                            case R.id.eg:
                                countries.index = 14;
                                c = countries.countryCodes[14];
                                break;
                            case R.id.fr:
                                countries.index = 15;
                                c = countries.countryCodes[15];
                                break;
                            case R.id.gb:
                                countries.index = 16;
                                c = countries.countryCodes[16];
                                break;
                            case R.id.gr:
                                countries.index = 17;
                                c = countries.countryCodes[17];
                                break;
                            case R.id.hk:
                                countries.index = 18;
                                c = countries.countryCodes[18];
                                break;
                            case R.id.hu:
                                countries.index = 19;
                                c = countries.countryCodes[19];
                                break;
                            case R.id.id:
                                countries.index = 20;
                                c = countries.countryCodes[20];
                                break;
                            case R.id.ie:
                                countries.index = 21;
                                c = countries.countryCodes[21];
                                break;
                            case R.id.il:
                                countries.index = 22;
                                c = countries.countryCodes[22];
                                break;
                            case R.id.in:
                                countries.index = 23;
                                c = countries.countryCodes[23];
                                break;
                            case R.id.it:
                                countries.index = 24;
                                c = countries.countryCodes[24];
                                break;
                            case R.id.jp:
                                countries.index = 25;
                                c = countries.countryCodes[25];
                                break;
                            case R.id.kr:
                                countries.index = 26;
                                c = countries.countryCodes[26];
                                break;
                            case R.id.lt:
                                countries.index = 27;
                                c = countries.countryCodes[27];
                                break;
                            case R.id.lv:
                                countries.index = 28;
                                c = countries.countryCodes[28];
                                break;
                            case R.id.ma:
                                countries.index = 29;
                                c = countries.countryCodes[29];
                                break;
                            case R.id.mx:
                                countries.index = 30;
                                c = countries.countryCodes[30];
                                break;
                            case R.id.my:
                                countries.index = 31;
                                c = countries.countryCodes[31];
                                break;
                            case R.id.ng:
                                countries.index = 32;
                                c = countries.countryCodes[32];
                                break;
                            case R.id.nl:
                                countries.index = 33;
                                c = countries.countryCodes[33];
                                break;
                            case R.id.no:
                                countries.index = 34;
                                c = countries.countryCodes[34];
                                break;
                            case R.id.nz:
                                countries.index = 35;
                                c = countries.countryCodes[35];
                                break;
                            case R.id.ph:
                                countries.index = 36;
                                c = countries.countryCodes[36];
                                break;
                            case R.id.pl:
                                countries.index = 37;
                                c = countries.countryCodes[37];
                                break;
                            case R.id.pt:
                                countries.index = 38;
                                c = countries.countryCodes[38];
                                break;
                            case R.id.ro:
                                countries.index = 39;
                                c = countries.countryCodes[39];
                                break;
                            case R.id.rs:
                                countries.index = 40;
                                c = countries.countryCodes[40];
                                break;
                            case R.id.ru:
                                countries.index = 41;
                                c = countries.countryCodes[41];
                                break;
                            case R.id.sa:
                                countries.index = 42;
                                c = countries.countryCodes[42];
                                break;
                            case R.id.se:
                                countries.index = 43;
                                c = countries.countryCodes[43];
                                break;
                            case R.id.sg:
                                countries.index =44;
                                c = countries.countryCodes[44];
                                break;
                            case R.id.si:
                                countries.index = 45;
                                c = countries.countryCodes[45];
                                break;
                            case R.id.sk:
                                countries.index = 46;
                                c = countries.countryCodes[46];
                                break;
                            case R.id.th:
                                countries.index = 47;
                                c = countries.countryCodes[47];
                                break;
                            case R.id.tr:
                                countries.index = 48;
                                c = countries.countryCodes[48];
                                break;
                            case R.id.tw:
                                countries.index = 49;
                                c = countries.countryCodes[49];
                                break;
                            case R.id.ua:
                                countries.index = 50;
                                c = countries.countryCodes[50];
                                break;
                            case R.id.us:
                                countries.index = 51;
                                c = countries.countryCodes[51];
                                break;
                            case R.id.ve:
                                countries.index = 52;
                                c = countries.countryCodes[52];
                                break;
                            case R.id.za:
                                countries.index = 53;
                                c = countries.countryCodes[53];
                                break;

                            default:
                                c = "in";

                        }
                        businessFragment.country = c;
                        entertainmentFragment.country = c;
                        healthFragment.country = c;
                        homeFragment.country = c;
                        scienceFragment.country = c;
                        sportsFragment.country = c;
                        technologyFragment.country = c;
                    }
    });

}

}