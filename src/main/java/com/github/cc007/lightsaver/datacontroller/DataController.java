/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.cc007.lightsaver.datacontroller;

import com.github.cc007.lightsaver.message.rabbitmq.RMQMessageReceiver;
import com.github.cc007.lightsaver.message.tcp.TCPMessageServer;

/**
 *
 * @author Rik
 */
public class DataController {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        RMQMessageReceiver asr = new RMQMessageReceiver();
		asr.start();
    }
    
}
