package com.github.cc007.lightsaver.detector.motion;

import com.github.cc007.lightsaver.message.udp.UDPMessageClient;
import com.github.cc007.lightsaver.message.MessageTypes;
import com.github.cc007.lightsaver.message.Message;
import java.nio.ByteBuffer;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MotionDetectorClient extends UDPMessageClient {

    protected int clientId;

    public MotionDetectorClient(int clientId) {
        super("Passage detector #" + clientId);
        this.clientId = clientId;
    }

    private static boolean detectMotion() {
        Random r = new Random(System.currentTimeMillis());
        return r.nextBoolean(); //TODO really detect passage
    }

    @Override
    protected void doBefore() {
        super.doBefore();
        send = detectMotion();
    }

    @Override
    protected Message createMessage() {
        return new MotionDetectorMessage(MessageTypes.MOTION_DETECTOR_MSG, clientId);
    }

    @Override
    protected byte[] writeToBuffer() {
        return ByteBuffer
                .allocate(8)
                .putInt(0, m.getMsgType())
                .putInt(4, ((MotionDetectorMessage) m).getClientId())
                .array();
    }

    @Override
    protected int getMessageSize() {
        return 8;
    }

    @Override
    protected void doAfter() {
        try {
            //wait 5 seconds
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(MotionDetectorClient.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

}
