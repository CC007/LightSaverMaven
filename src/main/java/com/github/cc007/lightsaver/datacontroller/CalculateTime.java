/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.cc007.lightsaver.datacontroller;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import org.rug.netcomputing.rmi.base.Compute;
/**
 *
 * @author Aerylia
 */
public class CalculateTime {

    public static void main(String args[]) {
        //TODO fix this.
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        try {
            String name = "Compute";
            Registry registry = LocateRegistry.getRegistry(args[0]);
            Compute comp = (Compute) registry.lookup(name);
        } catch (Exception e) {
            System.err.println("ComputePi exception:");
            e.printStackTrace();
        }
    }    
}