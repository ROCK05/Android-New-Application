package com.example.newsapplication;

import com.example.newsapplication.fragments.businessFragment;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class countries {
    public static int index = 23;
    public static  Map<Integer, String> countryList = new HashMap<>();
    public static String[] countryCodes = {"ae", "ar", "at", "au",
            "be", "bg", "br",
            "ca", "ch", "cn", "co", "cu", "cz",
            "de", "eg", "fr", "gb", "gr", "hk",
            "hu", "id", "ie", "il", "in", "it", "jp",
            "kr", "lt", "lv", "ma", "mx", "my", "ng", "nl",
            "no", "nz", "ph", "pl", "pt", "ro", "rs", "ru",
            "sa", "se", "sg", "si", "sk", "th", "tr", "tw", "ua", "us", "ve", "za"};

    static {
        for (int i = 0; i < countries.countryCodes.length; i++) {
            countries.countryList.put(i, countryCodes[i]);
        }

//        static{
//            countries.index = 0;
//        }

    }

}




