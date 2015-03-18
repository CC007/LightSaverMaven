package com.github.cc007.lightsaver.templates.sendsize;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.nio.ByteBuffer;

public class SendSizeUDPServer {
    /* DONT USE, NOT SAFE YET!! */

    public static final int SERVER_PORT = 7376;

    private static DatagramSocket s = null;
    private static SendSizeUDPMessage m;
    private static byte[] mLength;
    private static byte[] mBuffer;
    private static ByteArrayInputStream bais;
    private static ObjectInputStream ois;

    public static void main(String[] args) {
        try {
            s = new DatagramSocket(SERVER_PORT);
            mLength = new byte[4];
            while (true) {
                DatagramPacket mPacket = new DatagramPacket(mLength, 4);
                s.receive(mPacket);

                mBuffer = new byte[ByteBuffer.wrap(mLength).getInt()];

                mPacket = new DatagramPacket(mBuffer, mBuffer.length);
                s.receive(mPacket);

                bais = new ByteArrayInputStream(mBuffer);
                ois = new ObjectInputStream(bais);
                m = (SendSizeUDPMessage) ois.readObject();

                System.out.println("Value from client " + m.getSensorId() + ": " + m.getValue());
            }
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (s != null) {
                s.close();
            }
        }
    }

}
