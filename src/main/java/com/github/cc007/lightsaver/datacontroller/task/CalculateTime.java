/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.cc007.lightsaver.datacontroller.task;

import com.github.cc007.lightsaver.appliance.light.Light;
import com.github.cc007.lightsaver.datacontroller.DataController;
import com.github.cc007.lightsaver.datacontroller.storage.Entry;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

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
    private int applianceId;

    private DataController dc;

    public CalculateTime(int mode) {
        this(mode, 0, Calendar.getInstance().getTimeInMillis(), 0);
    }

    public CalculateTime(int mode, long startDate, long endDate) {
        this(mode, startDate, endDate, 0);
    }

    public CalculateTime(int mode, long startDate, long endDate, int applianceId) {
        this.mode = mode;
        this.startDate = startDate;
        this.endDate = endDate;
        this.applianceId = applianceId;
    }

    @Override
    public Integer execute() {
        return computeTime();
    }

    private Integer computeTime() {
        Integer returnValue = 0;

        Set<List<Entry>> entries = dc.getEntries(startDate, endDate, applianceId);
        
        Integer accumTime = 0;
        Integer usage = 0;
        Iterator<List<Entry>> it = entries.iterator();
        while(it.hasNext()){
             List<Entry> e = it.next();
             long time = getTime(e);
             double usagePerSec = 1.0; //TODO get this from somewhere using applianceId.
             accumTime += (int) time;
             usage += (int)(time*usagePerSec);
        }
        
        switch (mode){
            case CalculateTime.SECONDS:
                return accumTime/1000;
            case CalculateTime.HOURS:
                return accumTime/60/60/1000;
            case CalculateTime.ELECTRICITY_USAGE:
                return usage;
                
        }
        return returnValue;
    }
    
    private long getTime(List<Entry> entries){
        boolean prevOn = false;
        long accumTime = 0;
        long time = 0;
        Iterator<Entry> it = entries.iterator();
        while (it.hasNext()) {
            Entry e = it.next();
            if(e.getState() == Light.LIGHT_ON){
                if(!prevOn){
                    time = e.getDate();
                    prevOn = true;
                }
            } else {
                if (prevOn) {
                    accumTime += e.getDate() - time;
                    prevOn = false;
                }
            }
            
        }
        return accumTime;
    }
    
    @Override
    public void setDataController(DataController dc) {
        this.dc = dc;
    }
}
