/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.cc007.lightsaver.light;

import com.github.cc007.lightsaver.detector.door.DoorDetectorMessage;
import com.github.cc007.lightsaver.message.Message;
import com.github.cc007.lightsaver.message.MessageTypes;
import com.github.cc007.lightsaver.message.tcp.TCPMessageProtocol;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rik
 */
public class DetectorTCPMessageProtocol implements TCPMessageProtocol {

    @Override
    public void processInput(int type, DataOutputStream out, DataInputStream in, Message m) {
        try {
            switch (type) {
                case MessageTypes.DOOR_DETECTOR_MSG:
                    m = new DoorDetectorMessage(type, in.readInt(), in.readBoolean());
                    System.out.println("The state of door " + ((DoorDetectorMessage) m).getClientId() + " changed its state to: " + (((DoorDetectorMessage) m).isOpen() ? "open" : "closed"));
                    break;
                default:
                    System.err.println("Unknown message type found: " + type);
            }
        } catch (IOException ex) {
            Logger.getLogger(DetectorTCPMessageProtocol.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
