/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.cc007.lightsaver.appliance.light;

import com.github.cc007.lightsaver.appliance.ApplianceStateSender;
import com.github.cc007.lightsaver.appliance.ElectricalAppliance;
import com.github.cc007.lightsaver.message.tcp.TCPMessageServer;
import com.github.cc007.lightsaver.message.udp.UDPMessageServer;
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
    public static final int COUNTER_RESET_VALUE = 2;
    public static final int MOTION_DETECTOR_COUNTER_RESET_VALUE = 2;

    private UDPMessageServer udpServer;
    private TCPMessageServer tcpServer;
    private ApplianceStateSender sender;

    private int lightlevel;
    private int counter;
    private int motionDetectorCounter;

    public Light() {
        this.state = LIGHT_OFF;
        this.counter = 0;
        this.lightlevel = 0;
        this.motionDetectorCounter = 0;
    }

    public void startThreads(String serverAddress, int clientId) {
        udpServer = new UDPMessageServer(new DetectorUDPMessageProtocol(this));
        tcpServer = new TCPMessageServer(new DetectorTCPMessageProtocol(this));
        sender = new ApplianceStateSender(serverAddress, clientId);
        sender.start();
        udpServer.start();
        tcpServer.start();
    }

    /**
     * @param args the command line arguments
     */
    private void checkDesiredState() {
        System.out.println("Check: counter = " + counter + ", lightlevel = " + lightlevel + ", state = " + state);
        if (counter != 0) {
            counter--;
            if (motionDetectorCounter != 0) {
                motionDetectorCounter--;
            }
            if (lightlevel < LIGHT_LVL_THRESHOLD) {
                System.out.println("Turn light on");
                setState(LIGHT_ON);
            }
        } else {
            System.out.println("Turn light off");
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
    
    public synchronized int getMotionDetectorCounter() {
        return motionDetectorCounter;
    }

    public static void main(String[] args) {
        int clientId = 0;
        String serverAddress = null;
        if (args.length != 2) {
            System.err.println("Invalid use: needs 2 arguments, namely the client id and the server inet address.");
            System.exit(-1);
        } else {
            try {
                clientId = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                System.err.println("Argument " + args[0] + " must be an integer.");
                System.exit(-1);
            }
            serverAddress = args[1];
        }
        Light l = new Light();
        l.startThreads(serverAddress, clientId);
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
