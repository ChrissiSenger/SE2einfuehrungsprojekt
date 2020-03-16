package com.example.se2einfuehrungsprojekt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private Button buttonSend;
    private Button buttonChange;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonSend = (Button) findViewById(R.id.button);
        buttonChange = (Button) findViewById(R.id.button2);
        editText = (EditText) findViewById(R.id.editText);

        Intent intent = new Intent(this, SendToServerActivity.class);
        startActivity(intent);
    }
}
