/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.cc007.lightsaver.message.rabbitmq;

import com.github.cc007.lightsaver.message.Message;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rik
 */
public abstract class RMQMessageSender extends Thread {

    private static final String SERVER_ADDRESS = "localhost";

    protected Message m;
    protected boolean send;
    protected boolean exit;

    private byte[] mBuffer;
    private Connection con;
    private Channel ch;

    public RMQMessageSender() {
        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost(SERVER_ADDRESS);
            this.con = factory.newConnection();
            this.ch = con.createChannel();
            this.ch.queueDeclare(RMQMessageReceiver.QUEUE_NAME, false, false, false, null);
            this.send = true;
            this.exit = false;
        } catch (IOException ex) {
            Logger.getLogger(RMQMessageSender.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public RMQMessageSender(String name) {
        this();
        setName(name);
    }

    protected void doBefore() {
    }

    protected abstract Message createMessage();

    protected abstract byte[] writeToBuffer();

    protected void doAfter() {
    }

    @Override
    public void run() {
        while (!exit) {
            try {
                // things to be done before creating the message
                doBefore();

                if (send) {
                    // create the message
                    m = createMessage();

                    //write to buffer
                    mBuffer = writeToBuffer();

                    //connection part
                    ch.basicPublish("", RMQMessageReceiver.QUEUE_NAME, null, mBuffer);
                }
                // things to be done after sending the message
                doAfter();
            } catch (IOException ex) {
                Logger.getLogger(RMQMessageSender.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    ch.close();
                    con.close();
                } catch (IOException ex) {
                    Logger.getLogger(RMQMessageSender.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
