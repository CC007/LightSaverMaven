/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.cc007.lightsaver.datacontroller;

import com.github.cc007.lightsaver.datacontroller.task.DataTask;
import com.github.cc007.lightsaver.utils.ReferencableMethod;
import com.github.cc007.lightsaver.utils.TransactionHandler;
import java.io.Serializable;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;

/**
 *
 * @author Aerylia
 */
public class CalculateTime implements DataTask<Integer>, Serializable {

    private static final long serialVersionUID = 272L;
    DataController dc;
    // TODO impl constructor with mode, startDate, endDate, (roomID)

    @Override
    public Integer execute() {
        return computeTime();
    }

    private Integer computeTime() {
        //TODO Check if this works.
        Integer returnValue = 0;
        dc.getEntries(0L, 0L);//TODO Dummy
        //or
        dc.getEntries(0L, 0L, 0);//TODO Dummy
        return returnValue;
    }

    @Override
    public void setDataController(DataController dc) {
        this.dc = dc;
    }
}
