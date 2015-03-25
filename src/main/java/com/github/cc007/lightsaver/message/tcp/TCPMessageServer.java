package com.github.cc007.lightsaver.message.tcp;

import com.github.cc007.lightsaver.message.Message;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TCPMessageServer extends Thread {

    public static int SERVER_PORT = 7374;

    TCPMessageProtocol tcpmp;

    public TCPMessageServer(TCPMessageProtocol tcpmp) {
        this.tcpmp = tcpmp;
    }

    @Override
    public void run() {
        listen();
    }

    public void listen() {
        try {
            ServerSocket serverSocket = new ServerSocket(SERVER_PORT);
            System.out.println("Server is listening...");
            while (true) {
                Socket clientSocket = serverSocket.accept();
                //start a thread that waits for a client message coming in
                ConnectionThread ct = new ConnectionThread(clientSocket, tcpmp);
                ct.start();
            }
        } catch (IOException ex) {
            Logger.getLogger(TCPMessageServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    class ConnectionThread extends Thread {

        private DataInputStream in;
        private DataOutputStream out;
        private Socket clientSocket;
        TCPMessageProtocol tcpmp;

        private Message m;

        public ConnectionThread(Socket clientSocket, TCPMessageProtocol tcpmp) {
            try {
                this.tcpmp = tcpmp;
                this.clientSocket = clientSocket;
                in = new DataInputStream(this.clientSocket.getInputStream());
                out = new DataOutputStream(this.clientSocket.getOutputStream());
            } catch (IOException ex) {
                Logger.getLogger(TCPMessageServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        @Override
        public void run() {
            try {
                int type = in.readInt();
                tcpmp.processInput(type, out, in, m);
            } catch (IOException ex) {
                Logger.getLogger(TCPMessageServer.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    clientSocket.close();
                } catch (IOException ex) {
                    Logger.getLogger(TCPMessageServer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
