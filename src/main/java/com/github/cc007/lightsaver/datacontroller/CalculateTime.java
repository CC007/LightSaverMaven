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
import java.util.Calendar;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;

/**
 *
 * @author Aerylia
 */
public class CalculateTime implements DataTask<Integer>, Serializable {

    private static final long serialVersionUID = 272L;
    public static final int HOURS = 1;
    public static final int SECONDS = 2;
    public static final int ELECTRICITY_USAGE = 3;
    
    private int mode;
    private long startDate;
    private long endDate;
    private int roomID;
    
    DataController dc;
    
    public CalculateTime(int mode){
        this(mode, 0, Calendar.getInstance().getTimeInMillis(), 0);
    }
    
    public CalculateTime(int mode, long startDate, long endDate){
        this(mode, startDate, endDate, 0);
    }
    
    public CalculateTime(int mode, long startDate, long endDate, int roomID){
        this.mode = mode;
        this.startDate = startDate;
        this.endDate = endDate;
        this.roomID = roomID;
    }
    
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
