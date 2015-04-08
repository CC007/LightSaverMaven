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
import org.rug.netcomputing.rmi.base.Compute;
import org.rug.netcomputing.rmi.base.Task;

/**
 *
 * @author Aerylia
 */
public class ComputeEngine implements Compute {

    public ComputeEngine() {
        super();
    }

    @Override
    public <T> T executeTask(Task<T> t) {
        return t.execute();
    }
}