/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.cc007.lightsaver.detector.passage;

/**
 *
 * @author Rik
 */
public class PassageDetector {

    /**
     * @param args the command line arguments
     */
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
        PassageDetectorClient pdc = new  PassageDetectorClient(clientId, serverAddress);
        pdc.start();
    }
    
}
