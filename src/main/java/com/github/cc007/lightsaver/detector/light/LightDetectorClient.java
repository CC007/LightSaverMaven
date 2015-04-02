package com.github.cc007.lightsaver.detector.light;

import com.github.cc007.lightsaver.message.udp.UDPMessageClient;
import com.github.cc007.lightsaver.message.MessageTypes;
import com.github.cc007.lightsaver.message.Message;
import java.nio.ByteBuffer;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LightDetectorClient extends UDPMessageClient {

    protected int clientId;

    public LightDetectorClient(int clientId) {
        super("Light detector #" + clientId);
        this.clientId = clientId;
    }

    private static int detectLight() {
        Random r = new Random(System.currentTimeMillis());
        return r.nextInt(100); //TODO really detect light
    }

    @Override
    protected Message createMessage() {
        return new LightDetectorMessage(MessageTypes.LIGHT_DETECTOR_MSG, clientId, detectLight());
    }

    @Override
    protected byte[] writeToBuffer() {
        return ByteBuffer
                .allocate(12)
                .putInt(0, m.getMsgType())
                .putInt(4, ((LightDetectorMessage) m).getClientId())
                .putInt(8, ((LightDetectorMessage) m).getValue())
                .array();
    }

    @Override
    protected int getMessageSize() {
        return 12;
    }

    @Override
    protected void doAfter() {
        try {
            //wait 10 seconds
            Thread.sleep(10000);
        } catch (InterruptedException ex) {
            Logger.getLogger(LightDetectorClient.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

}
