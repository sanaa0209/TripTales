package com.unimib.triptales.ui.login;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.unimib.triptales.R;

public class LoadingActivity extends AppCompatActivity {
    private static final String TAG = LoadingActivity.class.getSimpleName();

    private VideoView videoView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        videoView = findViewById(R.id.videoView);
        Uri videoUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.caricamento_app);
        videoView.setVideoURI(videoUri);
        videoView.start();

        if (getIntent().getBooleanExtra("finish", false)) {
            Log.d(TAG, "Chiudo LoadingActivity");
            finish();
            return;
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (intent.getBooleanExtra("finish", false)) {
            Log.d(TAG, "Chiudo LoadingActivity da onNewIntent");
            finish();
        }
    }

}
