/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.cc007.lightsaver.datacontroller;

import com.github.cc007.lightsaver.message.Message;
import com.github.cc007.lightsaver.message.MessageTypes;
import com.github.cc007.lightsaver.message.rabbitmq.RMQMessageProtocol;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.nio.ByteBuffer;

/**
 *
 * @author Rik
 */
public class ApplianceStateMessageProtocol implements RMQMessageProtocol {
    DataController dc;

    public ApplianceStateMessageProtocol(DataController dc) {
        this.dc = dc;
    }
    
    @Override
    public void processInput(byte[] mBuffer, Message m){
        switch (ByteBuffer.wrap(mBuffer).getInt(0)) {
            case MessageTypes.APPLIANCE_STATE_CHANGE_MSG:
                m = new ApplianceStateMessage(MessageTypes.APPLIANCE_STATE_CHANGE_MSG, ByteBuffer.wrap(mBuffer).getInt(4), ByteBuffer.wrap(mBuffer).getInt(8));
                System.out.println("Something needs to be done with the logging of state " + ((ApplianceStateMessage)m).getState() + " for client: " + ((ApplianceStateMessage)m).getClientId() + " at time: " + System.currentTimeMillis());
                break;
            default:
                System.err.println("Unknown message type found: " + ByteBuffer.wrap(mBuffer).getInt(0));
        }
    }

}
