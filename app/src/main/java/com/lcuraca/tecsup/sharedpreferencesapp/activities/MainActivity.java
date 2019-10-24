package com.lcuraca.tecsup.sharedpreferencesapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.lcuraca.tecsup.sharedpreferencesapp.R;
import com.lcuraca.tecsup.sharedpreferencesapp.models.User;
import com.lcuraca.tecsup.sharedpreferencesapp.repositories.UserRepository;

public class MainActivity extends AppCompatActivity {

    private TextView fullnameText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fullnameText = findViewById(R.id.fullname_text);

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        String username = sp.getString("username", null);
        User user = UserRepository.findByUsername(username);

        if (user != null) {
            fullnameText.setText(user.getFullname());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.logout_item:
                callLogout();
                break;
            case R.id.encuesta_item:
                callEncuesta();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void callLogout() {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        sp.edit().remove("isLogged").commit();

        finish();
        startActivity(new Intent(this, LoginActivity.class));
    }

    private void callEncuesta() {
        startActivity(new Intent(this, EncuestaActivity.class));
    }
}
