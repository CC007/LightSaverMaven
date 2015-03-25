/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.cc007.lightsaver.datacollection;

/**
 *
 * @author Rik
 */
public interface Appliance {

    public void sendState();

    public void setState(int state);
}
