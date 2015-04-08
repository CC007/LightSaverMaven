/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.cc007.lightsaver.datacontroller;

import java.io.Serializable;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import org.rug.netcomputing.rmi.base.Compute;
import org.rug.netcomputing.rmi.base.Task;
/**
 *
 * @author Aerylia
 */
public class CalculateTime implements Task<Integer>, Serializable {   

    private static final long serialVersionUID = 272L;
    
    @Override
    public Integer execute() {
        //TODO fix this.
        /*
        * from the tutorial main of calculatePi
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        try {
            String name = "Compute";
            // args[0] not available here
            //Registry registry = LocateRegistry.getRegistry(args[0]);
            //Compute comp = (Compute) registry.lookup(name);
            //Pi task = new Pi(Integer.parseInt(args[1])); //Pi implements Task<BigDecimal>
            //BigDecimal pi = comp.executeTask(task);
            // System.out.println(pi);
        } catch (Exception e) {
            e.printStackTrace();
        }
                
        */
        return 1;
    }
}