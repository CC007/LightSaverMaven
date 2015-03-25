/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.cc007.lightsaver.detector.motion;

import com.github.cc007.lightsaver.message.Message;

/**
 *
 * @author Rik
 */
public class MotionDetectorMessage extends Message {

    private final int clientId;

    public MotionDetectorMessage(int msgType, int clientId) {
        super(msgType);
        this.clientId = clientId;
    }

    public int getClientId() {
        return clientId;
    }

}
