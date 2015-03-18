/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.cc007.lightsaver.light;

import com.github.cc007.lightsaver.message.tcp.TCPMessageProtocol;
import com.github.cc007.lightsaver.message.tcp.TCPMessageServer;
import com.github.cc007.lightsaver.message.udp.UDPMessageServer;

/**
 *
 * @author Rik
 */
public class Light {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        UDPMessageServer udpServer = new UDPMessageServer(new DetectorUDPMessageProtocol());
        TCPMessageServer tcpServer = new TCPMessageServer(new DetectorTCPMessageProtocol());
        udpServer.start();
        tcpServer.start();
    }
    
}
