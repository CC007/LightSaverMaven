/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.cc007.lightsaver.message.tcp;

import com.github.cc007.lightsaver.message.Message;
import java.io.DataInputStream;
import java.io.DataOutputStream;

/**
 *
 * @author Rik
 */
public interface TCPMessageProtocol {
    public void processInput(int type, DataOutputStream out, DataInputStream in, Message m);
    
}
