/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.cc007.lightsaver.datacontroller;

import com.github.cc007.lightsaver.message.Message;
import com.github.cc007.lightsaver.message.MessageTypes;
import com.github.cc007.lightsaver.message.rabbitmq.RMQMessageSender;

/**
 *
 * @author Rik
 */
public class ApplianceStateSender extends RMQMessageSender {

    private int clientId;
    private int state;

    public ApplianceStateSender(int clientId) {
        super("State change sender: " + clientId);
        this.clientId = clientId;
        this.state = 0;
    }

    @Override
    protected Message createMessage() {
        return new ApplianceStateMessage(MessageTypes.APPLIANCE_STATE_CHANGE_MSG, clientId, state);
    }

    @Override
    protected byte[] writeToBuffer() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
