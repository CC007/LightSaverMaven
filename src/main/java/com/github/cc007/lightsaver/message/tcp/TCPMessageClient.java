/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.cc007.lightsaver.message.tcp;

import com.github.cc007.lightsaver.message.Message;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rik
 */
public abstract class TCPMessageClient extends Thread {

    private static final String SERVER_ADDRESS = "localhost";

    protected Message m;
    protected boolean send;
    protected boolean exit;
    protected final Object sendLock = new Object();

    private DataOutputStream out;
    private DataInputStream in;
    private byte[] mBuffer;
    private Socket s = null;

    public TCPMessageClient() {
        this.send = true;
        this.exit = false;
    }

    public TCPMessageClient(String name) {
        this();
        setName(name);
    }

    protected void doBefore() {
    }

    protected abstract Message createMessage();

    protected abstract byte[] writeToBuffer();

    protected void doAfter() {
    }

    @Override
    public void run() {
        while (!exit) {
            try {
                synchronized (sendLock) {
                    // things to be done before creating the message
                    doBefore();

                    if (send) {
                        // create the message
                        m = createMessage();

                        //write to buffer
                        mBuffer = writeToBuffer();

                        //connection part
                        s = new Socket(SERVER_ADDRESS, TCPMessageServer.SERVER_PORT);
                        in = new DataInputStream(s.getInputStream());
                        out = new DataOutputStream(s.getOutputStream());

                        // send message
                        out.write(mBuffer);
                    }

                    // things to be done after sending the message
                    doAfter();
                }
            } catch (IOException ex) {
                Logger.getLogger(TCPMessageClient.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                if (s != null) {
                    try {
                        s.close();
                    } catch (IOException ex) {
                        Logger.getLogger(TCPMessageClient.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
    }
}
