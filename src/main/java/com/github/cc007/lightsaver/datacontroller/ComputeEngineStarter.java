/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.cc007.lightsaver.datacontroller;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.rug.netcomputing.rmi.base.Compute;
import org.rug.netcomputing.rmi.base.RmiStarter;

/**
 *
 * @author Aerylia
 */
public class ComputeEngineStarter extends RmiStarter {

    DataController dc;

    public ComputeEngineStarter(DataController dc) {
        this.dc = dc;
    }

    @Override
    public void start() {
        //TODO check if this is correct
        System.out.println(" Set security manager");
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        try {
            System.out.println(" Make compute engine");
            Compute engine = new DataComputeEngine(dc);
            System.out.println(" Export compute engine");
            Compute stub = (Compute) UnicastRemoteObject.exportObject(engine, 0);
            System.out.println(" Get registry");
            Registry registry = LocateRegistry.getRegistry();
            System.out.println(" Trying to bind DataComputeEngine");
            registry.rebind(Compute.SERVICE_NAME, stub);
            System.out.println("DataComputeEngine bound");
        } catch (RemoteException ex) {
            System.out.println("DataComputeEngine failed to bind!");
            Logger.getLogger(ComputeEngineStarter.class.getName()).log(Level.SEVERE, null, ex);
        }catch(Exception ex){
            System.out.println("Unexpected exception occurred!");
            Logger.getLogger(ComputeEngineStarter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
