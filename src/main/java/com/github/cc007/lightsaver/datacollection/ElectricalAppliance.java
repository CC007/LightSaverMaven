/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.cc007.lightsaver.datacollection;

public abstract class ElectricalAppliance {

    protected int state;

    public void setState(int state) {
        this.state = state;
        sendState();
    }

    public abstract void sendState();

}
