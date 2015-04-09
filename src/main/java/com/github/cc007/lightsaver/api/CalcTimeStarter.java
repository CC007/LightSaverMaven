/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.cc007.lightsaver.api;

import org.rug.netcomputing.rmi.base.RmiStarter;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import org.rug.netcomputing.rmi.base.Compute;
import org.rug.netcomputing.rmi.base.RmiStarter;
import com.github.cc007.lightsaver.datacontroller.CalculateTime;
import java.util.Calendar;

/**
 *
 * @author Aerylia
 */
public class CalcTimeStarter extends RmiStarter {

    private final int mode;
    private final int applianceId;
    private final long startDate;
    private final long endDate;

    private Integer result;

    public CalcTimeStarter(int mode, long startDate, long endDate, int applianceId) {
        this.mode = mode;
        this.startDate = startDate;
        this.endDate = endDate;
        this.applianceId = applianceId;
    }

    public CalcTimeStarter(int mode, long startDate, long endDate) {
        this.mode = mode;
        this.startDate = startDate;
        this.endDate = endDate;
        this.applianceId = 0;
    }

    @Override
    public void start() {
        try {
            Registry registry = LocateRegistry.getRegistry();
            Compute compute = (Compute) registry.lookup(Compute.SERVICE_NAME);
            CalculateTime task = new CalculateTime(mode, startDate, endDate, applianceId);
            result = compute.executeTask(task);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Integer getResult() {
        return result;
    }
    

}
