/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.cc007.lightsaver.light;

import com.github.cc007.lightsaver.datacollection.ElectricalAppliance;
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
    public static final int LIGHT_COUNTER_RESET_VALUE = 50;
    public static final int MOTION_DETECTOR_COUNTER_RESET_VALUE = 5;

    private UDPMessageServer udpServer;
    private TCPMessageServer tcpServer;
    private ApplianceStateSender sender;
    
    private int lightlevel;
    private int counter;
    private int motionDetectorCounter;

    public Light() {
        state = LIGHT_OFF;
        counter = 0;
        lightlevel = 0;
        motionDetectorCounter = 0;
    }
    
    public void startThreads(){
        udpServer = new UDPMessageServer(new DetectorUDPMessageProtocol(this));
        tcpServer = new TCPMessageServer(new DetectorTCPMessageProtocol(this));
        Random r = new Random(System.currentTimeMillis());
        int clientId = r.nextInt(1000); //TODO make client id unique
        sender = new ApplianceStateSender(clientId);
        sender.start();
        udpServer.start();
        tcpServer.start();
    }

    /**
     * @param args the command line arguments
     */
    private void checkDesiredState() {
        if (counter != 0 && (lightlevel < LIGHT_LVL_THRESHOLD || state == LIGHT_ON)) {
            setState(LIGHT_ON);
            counter--;
            motionDetectorCounter--;            
        } else {
            setState(LIGHT_OFF);
        }
    }

    public void setLightlevel(int lightlevel) {
        this.lightlevel = lightlevel;
    }

    public synchronized void setCounter(int counter) {
        this.counter = counter;
    }

    public synchronized void setMotionDetectorCounter(int motionDetectorCounter) {
        this.motionDetectorCounter = motionDetectorCounter;
    }

    public static void main(String[] args) {
        Light l = new Light();
        l.startThreads();
        while (true) {
            l.checkDesiredState();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Light.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void sendState() {
        sender.setState(state);
    }

}
