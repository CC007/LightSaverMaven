/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.cc007.lightsaver.message.rabbitmq;

import com.github.cc007.lightsaver.message.Message;

/**
 *
 * @author Rik
 */
public interface RMQMessageProtocol {
	
    public void processInput(byte[] buffer, Message m);
}
