/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.cc007.lightsaver.detector.door;

import com.github.cc007.lightsaver.message.Message;

/**
 *
 * @author Rik
 */
public class DoorDetectorMessage extends Message {

    private final int clientId;
    private final boolean open;

    public DoorDetectorMessage(int msgType, int clientId, boolean open) {
        super(msgType);
        this.clientId = clientId;
        this.open = open;
    }

    public int getClientId() {
        return clientId;
    }

    public boolean isOpen() {
        return open;
    }

    public boolean isClosed() {
        return !open;
    }
}
