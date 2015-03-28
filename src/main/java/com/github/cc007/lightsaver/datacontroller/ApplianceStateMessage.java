/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.cc007.lightsaver.datacontroller;

import com.github.cc007.lightsaver.message.Message;

/**
 *
 * @author Rik
 */
public class ApplianceStateMessage extends Message {

    private int clientId;
    private int state;

    public ApplianceStateMessage(int msgType, int clientId, int state) {
        super(msgType);
        this.clientId = clientId;
        this.state = state;
    }

    public int getClientId() {
        return clientId;
    }

    public int getState() {
        return state;
    }
}
