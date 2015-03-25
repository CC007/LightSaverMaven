/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.cc007.lightsaver.message;

/**
 * at the moment this class only contains constants. This may change in
 * a future implementation
 *
 * @author Rik
 */
public interface MessageTypes {

    // declare the max size of all message types below
    public static final int MAX_UDP_MSG_SIZE = 12;

    /* put here the type representation */
    // udp messages
    // - detector messages
    public static final int LIGHT_DETECTOR_MSG = 1000;      //size: 12
    public static final int PASSAGE_DETECTOR_MSG = 1010;    //size:  8
    public static final int MOTION_DETECTOR_MSG = 1020;     //size:  8

    // tcpmessages
    // - detector messages
    public static final int DOOR_DETECTOR_MSG = 2000;

    // rabbitmq messages
    // - appliance state change messages
    public static final int APPLIANCE_STATE_CHANGE_MSG = 3100;
}
