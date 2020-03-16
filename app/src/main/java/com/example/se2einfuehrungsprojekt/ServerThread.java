package com.example.se2einfuehrungsprojekt;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ServerThread implements Runnable {

    Socket client;

    public ServerThread (Socket connection) {
        this.client = connection;
    }

    public void run () {
        try {
                BufferedReader inFromClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
                DataOutputStream outToClient = new DataOutputStream(client.getOutputStream());
                String in = inFromClient.readLine();
                String answer = sortWithoutPrimes(in);
                outToClient.writeBytes(answer);

                inFromClient.close();
                outToClient.close();
                client.close();

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public String sortWithoutPrimes (String input) {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < input.length(); i++) {
            int temp = Integer.parseInt(input.substring(i, i+1));
            if (!checkPrime(temp)) {
                numbers.add(temp);
            }
        }
        Collections.sort(numbers);
        String retValue =
                numbers.toString()
                        .replaceAll(",", "")
                        .replaceAll("]", "")
                        .replaceAll("\\[", "");
        return retValue;
    }

    public boolean checkPrime (int  b) {
        boolean flag = true;
        for (int i = 2; i <= b/2; i++) {
            if (b%i == 0) {
                flag = false;
            }
        }
        return flag;
    }
}
