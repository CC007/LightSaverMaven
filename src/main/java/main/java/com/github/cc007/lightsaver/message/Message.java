/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.cc007.lightsaver.message;

import java.io.Serializable;

/**
 *
 * @author Rik
 */
public abstract class Message implements Serializable {

    private final int msgType;

    public Message(int msgType) {
        this.msgType = msgType;
    }

    public int getMsgType() {
        return msgType;
    }

}
