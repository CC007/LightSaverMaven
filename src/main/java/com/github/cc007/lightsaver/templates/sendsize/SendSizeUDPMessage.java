/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.cc007.lightsaver.templates.sendsize;

import java.io.Serializable;

/**
 *
 * @author Rik
 */
public class SendSizeUDPMessage implements Serializable{
    /* Example message */
    private final int sensorId;
    private final int value;

    public SendSizeUDPMessage(int sensorId, int value) {
        this.sensorId = sensorId;
        this.value = value;
    }

    public int getSensorId() {
        return sensorId;
    }

    public int getValue() {
        return value;
    }
    
}
