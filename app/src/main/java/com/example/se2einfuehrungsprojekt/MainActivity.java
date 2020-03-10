package com.example.se2einfuehrungsprojekt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goAhead (View view) {
        Intent intent = new Intent(MainActivity.this, SendToServerActivity.class);
        startActivity(intent);
    }
}
