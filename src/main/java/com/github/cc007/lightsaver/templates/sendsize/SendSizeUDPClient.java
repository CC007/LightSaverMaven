package com.github.cc007.lightsaver.templates.sendsize;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.util.Random;

public class SendSizeUDPClient {
    /* DONT USE, NOT SAFE YET!! */
    private static final String SERVER_ADDRESS = "localhost";

    private static int clientId;

    private static SendSizeUDPMessage m;
    private static byte[] mBuffer;
    private static byte[] mLenght;
    private static ByteArrayOutputStream baos;
    private static ObjectOutputStream oos;
    private static DatagramSocket s = null;

    private static int detectLight() {
        Random r = new Random(System.currentTimeMillis());
        return r.nextInt(100); //TODO really detect light
    }

    public static void main(String[] args) {
        Random r = new Random(System.currentTimeMillis());
        clientId = r.nextInt(1000); //TODO set clientId depending on the room
        while (true) {
            try {
                // message part
                baos = new ByteArrayOutputStream();
                oos = new ObjectOutputStream(baos);

                // detect new light level
                m = new SendSizeUDPMessage(clientId, detectLight());
                oos.writeObject(m);

                // generate byte array and its lenght
                mBuffer = baos.toByteArray();
                mLenght = ByteBuffer.allocate(4).putInt(mBuffer.length).array();
                System.out.println(mBuffer.length);
                // connection part
                s = new DatagramSocket();
                InetAddress lightDetectorServer = InetAddress.getByName(SERVER_ADDRESS);

                // send message size
                DatagramPacket ldmPacket = new DatagramPacket(mLenght, 4, lightDetectorServer, SendSizeUDPServer.SERVER_PORT);
                s.send(ldmPacket);

                //send message
                ldmPacket = new DatagramPacket(mBuffer, mBuffer.length, lightDetectorServer, SendSizeUDPServer.SERVER_PORT);
                s.send(ldmPacket);

                Thread.sleep(5000);
            } catch (SocketException e) {
                e.printStackTrace();
            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
