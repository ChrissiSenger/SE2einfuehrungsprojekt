package com.example.se2einfuehrungsprojekt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class MainActivity extends AppCompatActivity {

    private Button sendButton;
    private Button changeButton;
    private EditText editText;
    private String matrNr;
    private TextView textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void sendMatrNr(View view) {
        editText = (EditText) findViewById(R.id.editText);
        sendButton = (Button) findViewById(R.id.button);
        changeButton = (Button) findViewById(R.id.button2);
        textView2 = (TextView) findViewById(R.id.textView2);
        matrNr = editText.getText().toString();
        SendMessage sendMessage = new SendMessage();
        sendMessage.execute(matrNr);

    }

    public void changeMatrNr(View view) {

    }

    private class SendMessage extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            try {

                //BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
                Socket client = new Socket("se2-isys.aau.at", 53212); // connect to the server
                DataOutputStream outToServer = new DataOutputStream(client.getOutputStream());
                BufferedReader inFromServer = new BufferedReader(new InputStreamReader(client.getInputStream()));
                //String sentence = inFromUser.readLine();

                outToServer.writeBytes(matrNr+'\n'); // write the message to output stream
                //outToServer.writeBytes(sentence+'\n');
                String answer = inFromServer.readLine();
                client.close(); // closing the connection
                return answer;

            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute (String result) {
            textView2.setText(result);
        }
    }


}
