/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.cc007.lightsaver.detector.light;

import java.util.Random;

/**
 *
 * @author Rik
 */
public class LightDetectorClientStarter {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Random r = new Random(System.currentTimeMillis());
        int clientId = r.nextInt(1000); //TODO make client id unique
        LightDetectorClient ldc = new LightDetectorClient(clientId);
        ldc.start();
    }
    
}
