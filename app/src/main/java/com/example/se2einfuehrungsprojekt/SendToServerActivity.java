package com.example.se2einfuehrungsprojekt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class SendToServerActivity extends AppCompatActivity {

    private EditText editText;
    private String matrNr;
    private TextView textView2;
    private String message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_to_server);

        editText = (EditText) findViewById(R.id.editText);
        textView2 = (TextView) findViewById(R.id.textView2);
    }

    public void sendMatrNr(View view) {
        matrNr = editText.getText().toString();
        message = "send";
        SendMessage sendMessage = new SendMessage();
        sendMessage.execute(message);
    }

    public void changeMatrNr(View view) {
        matrNr = editText.getText().toString();
        message = "change";
        SendMessage sendMessage = new SendMessage();
        sendMessage.execute(message);
    }

    private class SendMessage extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            try {

                Socket client;
                if (params[0].equals("send")) {
                    client = new Socket("se2-isys.aau.at", 53212); // connect to the server
                } else if (params[0].equals("change")) {
                    client = new Socket("10.0.2.2", 2020); // connect to the server
                } else {
                    client = null;
                    return "Not worked";
                }
                DataOutputStream outToServer = new DataOutputStream(client.getOutputStream());
                BufferedReader inFromServer = new BufferedReader(new InputStreamReader(client.getInputStream()));

                outToServer.writeBytes(matrNr+'\n'); // write the message to output stream
                String answer = inFromServer.readLine();
                outToServer.close();
                inFromServer.close();
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
            System.out.println(result);
        }
    }
}
