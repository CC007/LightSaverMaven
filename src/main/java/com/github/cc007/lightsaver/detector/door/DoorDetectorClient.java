/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.cc007.lightsaver.detector.door;

import com.github.cc007.lightsaver.message.Message;
import com.github.cc007.lightsaver.message.MessageTypes;
import com.github.cc007.lightsaver.message.tcp.TCPMessageClient;
import java.nio.ByteBuffer;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rik
 */
public class DoorDetectorClient extends TCPMessageClient {

    protected int clientId;
    private boolean doorstate;

    public DoorDetectorClient(int clientId, String serverAddress) {
        super("Door detector #" + clientId, serverAddress);
        this.clientId = clientId;
        this.doorstate = false;
    }

    private boolean getDoorState() {
        //placeholder: override this method
        Random r = new Random(System.currentTimeMillis());
        return r.nextBoolean();
    }

    @Override
    protected void doBefore() {
        send = getDoorState() == doorstate;
        doorstate = send ? !doorstate : doorstate;
    }

    @Override
    protected Message createMessage() {
        return new DoorDetectorMessage(MessageTypes.DOOR_DETECTOR_MSG, clientId, doorstate);
    }

    @Override
    protected byte[] writeToBuffer() {
        return ByteBuffer
                .allocate(9)
                .putInt(0, m.getMsgType())
                .putInt(4, ((DoorDetectorMessage) m).getClientId())
                .put(8, ((DoorDetectorMessage) m).isOpen() ? (byte) 1 : (byte) 0)
                .array();
    }

    @Override
    protected void doAfter() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(DoorDetectorClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

}
