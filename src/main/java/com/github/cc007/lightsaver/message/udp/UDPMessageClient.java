/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.cc007.lightsaver.message.udp;

import com.github.cc007.lightsaver.message.Message;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rik
 */
public abstract class UDPMessageClient extends Thread {

    protected Message m;
    protected boolean send;
    protected boolean exit;
    protected final Object sendLock = new Object();

    private byte[] mBuffer;
    private DatagramSocket s = null;
    private String serverAddress = "localhost";

    public UDPMessageClient(String serverAddress) {
        this.send = true;
        this.exit = false;
        this.serverAddress = serverAddress;
    }

    public UDPMessageClient(String name, String serverAddress) {
        this(serverAddress);
        setName(name);
    }

    protected void doBefore() {
    }

    protected abstract Message createMessage();

    protected abstract byte[] writeToBuffer();

    protected abstract int getMessageSize();

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

                        // connection part
                        s = new DatagramSocket();
                        InetAddress lightDetectorServer = InetAddress.getByName(serverAddress);

                        // send message
                        DatagramPacket ldmPacket = new DatagramPacket(mBuffer, getMessageSize(), lightDetectorServer, UDPMessageServer.SERVER_PORT);
                        s.send(ldmPacket);
                    }

                    // things to be done after sending the message
                    doAfter();
                }
            } catch (SocketException ex) {
                Logger.getLogger(UDPMessageClient.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnknownHostException ex) {
                Logger.getLogger(UDPMessageClient.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(UDPMessageClient.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
