package com.github.cc007.lightsaver.detector.passage;

import com.github.cc007.lightsaver.message.udp.UDPMessageClient;
import com.github.cc007.lightsaver.message.MessageTypes;
import com.github.cc007.lightsaver.message.Message;
import java.nio.ByteBuffer;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PassageDetectorClient extends UDPMessageClient {

    protected int clientId;

    public PassageDetectorClient(int clientId, String serverAddress) {
        super("Passage detector #" + clientId, serverAddress);
        this.clientId = clientId;
    }

    private static boolean detectPassage() {
        // placeholder: override this method
        Random r = new Random(System.currentTimeMillis());
        int value = r.nextInt(1000);
        return value > 950;
    }

    @Override
    protected void doBefore() {
        super.doBefore();
        send = detectPassage();
    }

    @Override
    protected Message createMessage() {
        return new PassageDetectorMessage(MessageTypes.PASSAGE_DETECTOR_MSG, clientId);
    }

    @Override
    protected byte[] writeToBuffer() {
        return ByteBuffer
                .allocate(8)
                .putInt(0, m.getMsgType())
                .putInt(4, ((PassageDetectorMessage) m).getClientId())
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
            Logger.getLogger(PassageDetectorClient.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

}
