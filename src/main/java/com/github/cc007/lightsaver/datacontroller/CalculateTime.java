/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.cc007.lightsaver.datacontroller;

import com.github.cc007.lightsaver.appliance.light.Light;
import com.github.cc007.lightsaver.datacontroller.storage.Entry;
import com.github.cc007.lightsaver.datacontroller.task.DataTask;
import com.github.cc007.lightsaver.utils.ReferencableMethod;
import com.github.cc007.lightsaver.utils.TransactionHandler;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Set;
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
    private int applianceID;

    DataController dc;

    public CalculateTime(int mode) {
        this(mode, 0, Calendar.getInstance().getTimeInMillis(), 0);
    }

    public CalculateTime(int mode, long startDate, long endDate) {
        this(mode, startDate, endDate, 0);
    }

    public CalculateTime(int mode, long startDate, long endDate, int applianceID) {
        this.mode = mode;
        this.startDate = startDate;
        this.endDate = endDate;
        this.applianceID = applianceID;
    }

    @Override
    public Integer execute() {
        return computeTime();
    }

    private Integer computeTime() {
        //TODO Check if this works.
        Integer returnValue = 0;

        Set<Set<Entry>> entries = dc.getEntries(startDate, endDate, applianceID);
        boolean prevOn = false;
        long accumTime = 0;
        long time = 0;/*
        Iterator<Entry> it = entries.iterator();
        while (it.hasNext()) {
            Entry e = it.next();
            if (applianceID == 0 || applianceID == e.getClientId()) {
                if (e.getPrice() == Light.LIGHT_ON) {
                    if (!prevOn) {
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
        }*/
        return returnValue;
    }

    @Override
    public void setDataController(DataController dc) {
        this.dc = dc;
    }
}
