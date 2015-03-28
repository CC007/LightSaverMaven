/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.cc007.lightsaver.light;

import com.github.cc007.lightsaver.datacollection.ElectricalAppliance;
import com.github.cc007.lightsaver.datacontroller.ApplianceStateSender;
import com.github.cc007.lightsaver.message.tcp.TCPMessageProtocol;
import com.github.cc007.lightsaver.message.tcp.TCPMessageServer;
import com.github.cc007.lightsaver.message.udp.UDPMessageServer;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rik
 */
public class Light extends ElectricalAppliance {

    public static final int LIGHT_OFF = 0;
    public static final int LIGHT_ON = 1;
    public static final int LIGHT_LVL_THRESHOLD = 50;

    private UDPMessageServer udpServer;
    private TCPMessageServer tcpServer;
    private int lightlevel;
    private int counter;

    public Light() {
        state = LIGHT_OFF;
        udpServer = new UDPMessageServer(new DetectorUDPMessageProtocol());
        tcpServer = new TCPMessageServer(new DetectorTCPMessageProtocol());
        udpServer.start();
        tcpServer.start();
    }

    /**
     * @param args the command line arguments
     */
    private void checkDesiredState() {
        if (counter > 0 && (lightlevel < LIGHT_LVL_THRESHOLD || state == LIGHT_ON)) {
            setState(LIGHT_ON);
        } else {
            setState(LIGHT_OFF);
        }
    }

    public void setLightlevel(int lightlevel) {
        this.lightlevel = lightlevel;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public static void main(String[] args) {
        Light l = new Light();
        while (true) {
            l.checkDesiredState();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Light.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void sendState() {
        Random r = new Random(System.currentTimeMillis());
        int clientId = r.nextInt(1000); //TODO make client id unique
        ApplianceStateSender s = new ApplianceStateSender(clientId);
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
