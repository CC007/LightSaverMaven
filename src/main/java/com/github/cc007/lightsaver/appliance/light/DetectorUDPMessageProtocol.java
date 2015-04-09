/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.cc007.lightsaver.appliance.light;

import com.github.cc007.lightsaver.detector.light.LightDetectorMessage;
import com.github.cc007.lightsaver.detector.motion.MotionDetectorMessage;
import com.github.cc007.lightsaver.detector.passage.PassageDetectorMessage;
import com.github.cc007.lightsaver.message.Message;
import com.github.cc007.lightsaver.message.MessageTypes;
import com.github.cc007.lightsaver.message.udp.UDPMessageProtocol;
import java.nio.ByteBuffer;

/**
 *
 * @author Rik
 */
public class DetectorUDPMessageProtocol implements UDPMessageProtocol {

    Light l;

    public DetectorUDPMessageProtocol(Light l) {
        this.l = l;
    }

    @Override
    public void processInput(byte[] mBuffer, Message m) {
        switch (ByteBuffer.wrap(mBuffer).getInt(0)) {
            case MessageTypes.LIGHT_DETECTOR_MSG:
                // it's a Light detector value message
                m = new LightDetectorMessage(MessageTypes.LIGHT_DETECTOR_MSG, ByteBuffer.wrap(mBuffer).getInt(4), ByteBuffer.wrap(mBuffer).getInt(8));
                l.setLightlevel(((LightDetectorMessage) m).getValue());
                System.out.println("Light detector " + ((LightDetectorMessage) m).getClientId() + " set new light level value: " + ((LightDetectorMessage) m).getValue());
                break;
            case MessageTypes.PASSAGE_DETECTOR_MSG:
                // it's a Passage detector value message
                m = new PassageDetectorMessage(MessageTypes.LIGHT_DETECTOR_MSG, ByteBuffer.wrap(mBuffer).getInt(4));
                l.setCounter(l.COUNTER_RESET_VALUE);
                System.out.println("Passage detector " + ((PassageDetectorMessage) m).getClientId() + " reset counter to: " + l.COUNTER_RESET_VALUE);
                break;
            case MessageTypes.MOTION_DETECTOR_MSG:
                // it's a Passage detector value message
                m = new MotionDetectorMessage(MessageTypes.MOTION_DETECTOR_MSG, ByteBuffer.wrap(mBuffer).getInt(4));
                if (l.getMotionDetectorCounter() == 0) {
                    l.setCounter(-1); // infinite counter
                    System.out.println("Motion detector " + ((MotionDetectorMessage) m).getClientId() + " disabled counter");
                } else {
                    System.out.println("Motion detector " + ((MotionDetectorMessage) m).getClientId() + "'s input ignored");
                }
                break;
            default:
                System.err.println("Unknown message type found: " + ByteBuffer.wrap(mBuffer).getInt(0));
        }
    }

}
