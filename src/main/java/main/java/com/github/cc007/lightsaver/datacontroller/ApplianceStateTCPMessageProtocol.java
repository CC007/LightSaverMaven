/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.cc007.lightsaver.datacontroller;

import com.github.cc007.lightsaver.message.Message;
import com.github.cc007.lightsaver.message.MessageTypes;
import com.github.cc007.lightsaver.message.tcp.TCPMessageProtocol;
import java.io.DataInputStream;
import java.io.DataOutputStream;

/**
 *
 * @author Rik
 */
public class ApplianceStateTCPMessageProtocol implements TCPMessageProtocol {

    @Override
    public void processInput(int type, DataOutputStream out, DataInputStream in, Message m) {
        switch (type) {
            case MessageTypes.APPLIANCE_STATE_CHANGE_MSG:
                System.out.println("Something needs to be done with the light, but what is not yet specified :P");
                break;
            default:
                System.err.println("Unknown message type found: " + type);
        }
    }

}
