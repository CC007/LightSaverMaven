/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.cc007.lightsaver.datacontroller;

import com.github.cc007.lightsaver.utils.TransactionHandler;
import java.io.Serializable;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
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

    private Integer computeTime() {
        //TODO
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}