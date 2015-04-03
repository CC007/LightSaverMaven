/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.cc007.lightsaver.light;

import com.github.cc007.lightsaver.datacontroller.ApplianceStateMessage;
import com.github.cc007.lightsaver.message.Message;
import com.github.cc007.lightsaver.message.MessageTypes;
import com.github.cc007.lightsaver.message.rabbitmq.RMQMessageSender;
import java.nio.ByteBuffer;

/**
 *
 * @author Rik
 */
public class ApplianceStateSender extends RMQMessageSender {

    private final int clientId;
    private int state;

    public ApplianceStateSender(String serverAddress, int clientId) {
        super(serverAddress, "State change sender: " + clientId);
        this.clientId = clientId;
        this.state = 0;
        this.send = false;
    }

    @Override
    protected Message createMessage() {
        return new ApplianceStateMessage(MessageTypes.APPLIANCE_STATE_CHANGE_MSG, clientId, state);
    }

    @Override
    protected byte[] writeToBuffer() {
        return ByteBuffer
                .allocate(12)
                .putInt(0, m.getMsgType())
                .putInt(4, ((ApplianceStateMessage) m).getClientId())
                .putInt(8, ((ApplianceStateMessage) m).getState())
                .array();
    }

    @Override
    protected void doAfter() {
        send = false;
    }

    public void setState(int state) {
        synchronized (sendLock) {
            this.state = state;
            send = true;
        }
    }
}
