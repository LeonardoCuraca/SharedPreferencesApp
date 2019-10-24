package com.lcuraca.tecsup.sharedpreferencesapp.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.lcuraca.tecsup.sharedpreferencesapp.R;
import com.lcuraca.tecsup.sharedpreferencesapp.models.User;
import com.lcuraca.tecsup.sharedpreferencesapp.repositories.UserRepository;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class EncuestaActivity extends AppCompatActivity {

    private TextView fullname_tv;
    private EditText carrera_et;
    private RadioGroup radioGroup;
    private RadioButton rb_m, rb_f;
    private CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encuesta);

        fullname_tv = findViewById(R.id.fullname_tv);
        carrera_et = findViewById(R.id.carrera_et);
        radioGroup = findViewById(R.id.radioGroup);
        rb_m = findViewById(R.id.rb_m);
        rb_f = findViewById(R.id.rb_f);
        checkBox = findViewById(R.id.check);

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        String username = sp.getString("username", null);
        User user = UserRepository.findByUsername(username);

        if (user != null) {
            fullname_tv.setText(user.getFullname());
        }

        String carrera = sp.getString("carrera", null);

        if (carrera != null) {
            carrera_et.setText(carrera);
        }

        String sexo = sp.getString("sexo", null);

        if (sexo != null) {
            if (sexo.equals("M")) {
                radioGroup.check(R.id.rb_m);
            } else if (sexo.equals("F")) {
                radioGroup.check(R.id.rb_f);
            }
        }

        Boolean boxCheched = sp.getBoolean("isChecked", false);

        if (boxCheched != false) {
            checkBox.setChecked(true);
        }

    }

    @Override
    public void onBackPressed() {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        sp.edit().putString("carrera", carrera_et.getText().toString()).apply();

        int selectedId = radioGroup.getCheckedRadioButtonId();
        if (rb_m == (RadioButton) findViewById(selectedId)) {
            sp.edit().putString("sexo", "M").apply();
        } else if (rb_f == (RadioButton) findViewById(selectedId)) {
            sp.edit().putString("sexo", "F").apply();
        }

        if (checkBox.isChecked()) {
            sp.edit().putBoolean("isChecked", true).apply();
        } else {
            sp.edit().putBoolean("isChecked", false).apply();
        }

        super.onBackPressed();
    }
}
