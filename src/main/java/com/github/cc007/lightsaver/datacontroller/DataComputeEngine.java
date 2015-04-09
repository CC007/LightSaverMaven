/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.cc007.lightsaver.datacontroller;

import org.rug.netcomputing.rmi.base.Compute;
import org.rug.netcomputing.rmi.base.Task;

/**
 *
 * @author Aerylia
 */
public class DataComputeEngine implements Compute {

    DataController dc;

    public DataComputeEngine(DataController dc) {
        this.dc = dc;
    }

    @Override
    public <T> T executeTask(Task<T> t) {
        if (t instanceof DataTask) {
            ((DataTask<T>) t).setDataController(dc);
        }
        return t.execute();
    }
}