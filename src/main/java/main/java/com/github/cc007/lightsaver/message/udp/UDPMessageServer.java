package com.github.cc007.lightsaver.message.udp;

import com.github.cc007.lightsaver.message.MessageTypes;
import com.github.cc007.lightsaver.message.Message;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UDPMessageServer extends Thread{

    public static final int SERVER_PORT = 7376;

    private static DatagramSocket s = null;
    private static Message m;
    private final UDPMessageProtocol udpmp;

    public UDPMessageServer(UDPMessageProtocol udpmp) {
        this.udpmp = udpmp;
    }

    @Override
    public void run() {
        try {
            // connection part
            s = new DatagramSocket(SERVER_PORT);

            //allocate the buffer array
            while (true) {
                // receive databuffer
                byte[] mBuffer = new byte[MessageTypes.MAX_UDP_MSG_SIZE];
                DatagramPacket mPacket = new DatagramPacket(mBuffer, mBuffer.length);
                s.receive(mPacket);

                // construct and process message object from databuffer
                udpmp.processInput(mBuffer, m);
            }
        } catch (SocketException ex) {
            Logger.getLogger(UDPMessageServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UDPMessageServer.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (s != null) {
                s.close();
            }
        }
    }

}
