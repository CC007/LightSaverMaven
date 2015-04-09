/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.cc007.lightsaver.api;

import org.rug.netcomputing.rmi.base.RmiStarter;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import org.rug.netcomputing.rmi.base.Compute;
import org.rug.netcomputing.rmi.base.RmiStarter;
import com.github.cc007.lightsaver.datacontroller.CalculateTime;
import java.util.Calendar;
/**
 *
 * @author Aerylia
 */
public class CalcTimeStarter extends RmiStarter {

    @Override
    public void start() {
        try {
            Registry registry = LocateRegistry.getRegistry();
            Compute compute = (Compute) registry.lookup(Compute.SERVICE_NAME);
            CalculateTime task = new CalculateTime(CalculateTime.HOURS);
            Integer i = compute.executeTask(task);
            System.out.println("computed time: " + i);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        CalcTimeStarter cts = new CalcTimeStarter();
        cts.start();
    }

}
