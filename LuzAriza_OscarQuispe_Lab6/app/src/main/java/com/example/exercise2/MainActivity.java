package com.example.exercise2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Intent intent;
    String sound = "";
    Spinner spinner;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intent = new Intent(this, MusicService.class);

        spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        SharedPreferences myPref = getSharedPreferences("PrefFile", MODE_PRIVATE);
        SharedPreferences.Editor editor = myPref.edit();
        editor.putString("sound", "");
        editor.commit();

    }

    public void start(View v){
        if (Objects.equals(sound, "")){
            Toast.makeText(getApplicationContext(), "Nothing Selected", Toast.LENGTH_LONG).show();
            return;
        } else {
            SharedPreferences myPref = getSharedPreferences("PrefFile", MODE_PRIVATE);
            SharedPreferences.Editor editor = myPref.edit();
            editor.putString("sound", sound);
            editor.commit();
            Log.d("SAVING", sound);
            startService(intent);
        }
    }
    public void stop(View v){
        stopService(new Intent(this, MusicService.class));
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
        Log.d("PAS", String.valueOf(pos));
        switch(pos)
        {
            case 0:
                Toast.makeText(getApplicationContext(), "Select a sound", Toast.LENGTH_LONG).show();
                break;
            case 1:
                sound = "alarm";
                break;
            case 2:
                sound = "ringtone";
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> Parent) {
        Toast.makeText(getApplicationContext(), "Nothing Selected", Toast.LENGTH_LONG).show();
    }

}