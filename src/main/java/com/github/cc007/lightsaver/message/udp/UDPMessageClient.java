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

    private static final String SERVER_ADDRESS = "localhost";

    protected Message m;
    protected boolean send;
    protected boolean exit;

    private byte[] mBuffer;
    private DatagramSocket s = null;

    public UDPMessageClient() {
        this.send = true;
        this.exit = false;
    }

    public UDPMessageClient(String name) {
        this();
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
                // things to be done before creating the message
                doBefore();
                
                if (send) {
                    // create the message
                    m = createMessage();

                    //write to buffer
                    mBuffer = writeToBuffer();

                    // connection part
                    s = new DatagramSocket();
                    InetAddress lightDetectorServer = InetAddress.getByName(SERVER_ADDRESS);

                    // send message
                    DatagramPacket ldmPacket = new DatagramPacket(mBuffer, getMessageSize(), lightDetectorServer, UDPMessageServer.SERVER_PORT);
                    s.send(ldmPacket);
                }
                
                // things to be done after sending the message
                doAfter();
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
