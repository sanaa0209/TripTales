package com.unimib.triptales.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.os.LocaleListCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Set;

public class SharedPreferencesUtils {
    private static final String PREF_NAME = "TripTalesPrefs";
    private static final String KEY_IS_LOGGED_IN = "isLoggedIn";
    private static final String KEY_DIARY_ID = "current_diary_id";
    private static final String KEY_FIRST_ACCESS = "firstAccess";
    private static final String PREFS_NAME = "TriptalesPrefs";
    private static final String KEY_CHECKPOINT_DIARY_ID = "checkpoint_diary_id";
    private static final String KEY_NIGHT_MODE = "nightMode";
    private static final String KEY_LANGUAGE = "language";
    private static final String CHECKPOINT_DIARY_ID_KEY = "checkpoint_diary_id";
    private final Context context;

    public SharedPreferencesUtils(Context context){
        this.context = context;
    }

    public static void setLoggedIn(Context context, boolean isLoggedIn) {
        SharedPreferences sharedPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean(KEY_IS_LOGGED_IN, isLoggedIn);
        editor.apply();
    }

    public static boolean isLoggedIn(Context context) {
        SharedPreferences sharedPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return sharedPref.getBoolean(KEY_IS_LOGGED_IN, false);
    }

    // Salva il diaryId nelle SharedPreferences
    public static void saveDiaryId(Context context, String diaryId) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(KEY_DIARY_ID, diaryId);
        editor.apply();
    }

    // Metodo per salvare l'ID del CheckpointDiary
    public static void saveCheckpointDiaryId(Context context, int id) {
        SharedPreferences prefs = context.getSharedPreferences("TripTales", Context.MODE_PRIVATE);
        prefs.edit().putInt(CHECKPOINT_DIARY_ID_KEY, id).apply();
        Log.d("SharedPreferencesUtils", "Saved checkpoint diary ID: " + id);
    }

    public static void setCheckpointDiaryId(Context context, int checkpointDiaryId) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("YourAppPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("CHECKPOINT_DIARY_ID", checkpointDiaryId);
        editor.apply();

        Log.d("SharedPreferences", "Checkpoint Diary ID salvato: " + checkpointDiaryId);
    }



    public static int getCheckpointDiaryId(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("YourAppPrefs", Context.MODE_PRIVATE);
        int checkpointDiaryId = sharedPreferences.getInt("CHECKPOINT_DIARY_ID", 0);
        Log.d("SharedPreferences", "Checkpoint Diary ID recuperato: " + checkpointDiaryId);
        return checkpointDiaryId;
    }

    // Recupera il diaryId salvato
    public static String getDiaryId(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getString(KEY_DIARY_ID, null); // Ritorna null se non esiste
    }

    public static void saveImageCardItemId(Context context, int imageCardItemId) {
        SharedPreferences prefs = context.getSharedPreferences("TripTales", Context.MODE_PRIVATE);
        prefs.edit().putInt("imageCardItemId", imageCardItemId).apply();
    }



    // Rimuove il diaryId (es. logout)
    public static void clearDiaryId(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.remove(KEY_DIARY_ID);
        editor.apply();
    }

    public static String getLoggedUserId() {
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if (firebaseUser == null) {
            return null;
        }
        return firebaseUser.getUid();
    }

    public static String getLoggedUserEmail() {
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if (firebaseUser == null) {
            return null;
        }
        return firebaseUser.getEmail();
    }

    public static void saveNightMode(Context context, boolean isNightMode) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(KEY_NIGHT_MODE, isNightMode);
        editor.apply();
    }

    public static boolean isNightModeEnabled(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return prefs.getBoolean(KEY_NIGHT_MODE, false);
    }

    public static void applyNightMode(Context context) {
        boolean isNightMode = isNightModeEnabled(context);
        if (isNightMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

    public static void saveLanguage(Context context, String languageCode) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(KEY_LANGUAGE, languageCode);
        editor.apply();
    }

    public static String getSavedLanguage(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return prefs.getString(KEY_LANGUAGE, "it");
    }

    public static void applyLanguage(Context context) {
        String languageCode = getSavedLanguage(context);
        AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags(languageCode));
    }

}
