package com.example.music;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;

import java.util.Locale;

public class LocaleHelper {

    private static final String PREFERENCE = "app_lang";
    private static final String KEY_LANG = "lang";

    public static Context setLocale(Context context, String lang) {
        savePreference(context, lang);
        return updateResources(context, lang);
    }

    public static String getLanguage(Context context) {
        SharedPreferences pref = context.getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE);
        return pref.getString(KEY_LANG, "id");
    }

    @SuppressLint("ApplySharedPref")
    private static void savePreference(Context ctx, String lang) {
        SharedPreferences prefs = ctx.getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE);
        prefs.edit().putString(KEY_LANG, lang).commit();
    }

    private static Context updateResources(Context context, String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = context.getResources().getConfiguration();
        config.setLocale(locale);
        return context.createConfigurationContext(config);
    }
}
