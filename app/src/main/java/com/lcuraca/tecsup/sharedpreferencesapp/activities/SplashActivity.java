package com.lcuraca.tecsup.sharedpreferencesapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import com.lcuraca.tecsup.sharedpreferencesapp.R;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                nextActivity();
            }
        }, 2000);
    }

    private void nextActivity() {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        if (sp.getBoolean("isLogged", false)) {
            startActivity(new Intent(this, MainActivity.class));
        } else {
            startActivity(new Intent(this, LoginActivity.class));
        }
        finish();
    }
}
