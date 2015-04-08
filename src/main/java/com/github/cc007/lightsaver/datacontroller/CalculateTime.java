/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.cc007.lightsaver.datacontroller;

import com.github.cc007.lightsaver.datacontroller.storage.Entry;
import com.github.cc007.lightsaver.utils.ReferencableMethod;
import com.github.cc007.lightsaver.utils.TransactionHandler;
import java.io.Serializable;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import org.rug.netcomputing.rmi.base.Compute;
import org.rug.netcomputing.rmi.base.Task;
/**
 *
 * @author Aerylia
 */
public class CalculateTime extends TransactionHandler implements Task<Integer>, Serializable {   

    private static final long serialVersionUID = 272L;
    
    @Override
    public Integer execute() {
        return computeTime();
    }
    
    public ReferencableMethod compTime = new ReferencableMethod() {

        @Override
        public void execute(Object... args) {
            PersistenceManager pm = (PersistenceManager) args[0];
            Object[] argsArray = (Object[]) (args[1]);
            //TODO get entries and calc time
        }
    };
    private Integer computeTime() {
        //TODO Check if this works.
        Integer returnValue = 0;
        this.handleTransaction(JDOHelper.getPersistenceManagerFactory("Statelog"), compTime, new Object[]{returnValue});
        return returnValue;
    }
}