package com.example.se2einfuehrungsprojekt;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    static final int SERVER_PORT = 2020;

    public static void main(String[] args) {
        Socket con;
        try {
            ServerSocket serverSocket = new ServerSocket(Server.SERVER_PORT);
            System.out.println("Server running on port: " + Server.SERVER_PORT);

            while (true) {
                con = serverSocket.accept();
                if (con != null) {
                    ServerThread svrThread = new ServerThread(con);
                    new Thread(svrThread).start();
                }
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
