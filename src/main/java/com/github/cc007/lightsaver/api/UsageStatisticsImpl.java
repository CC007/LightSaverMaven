/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.cc007.lightsaver.api;

import com.github.cc007.lightsaver.datacontroller.CalculateTime;
import java.util.Date;

public class UsageStatisticsImpl implements UsageStatistics {

    @Override
    public int getHours(Date startDate, Date endDate) {
        CalcTimeStarter cts = new CalcTimeStarter(CalculateTime.HOURS, startDate.getTime(), endDate.getTime());
        cts.start();
        return cts.getResult();
    }

    @Override
    public int getHours(Date startDate, Date endDate, int applianceID) {
        CalcTimeStarter cts = new CalcTimeStarter(CalculateTime.HOURS, startDate.getTime(), endDate.getTime(),applianceID);
        cts.start();
        return cts.getResult();
    }

    @Override
    public int getSeconds(Date startDate, Date endDate) {
        CalcTimeStarter cts = new CalcTimeStarter(CalculateTime.SECONDS, startDate.getTime(), endDate.getTime());
        cts.start();
        return cts.getResult();
    }

    @Override
    public int getSeconds(Date startDate, Date endDate, int applianceID) {
        CalcTimeStarter cts = new CalcTimeStarter(CalculateTime.SECONDS, startDate.getTime(), endDate.getTime(), applianceID);
        cts.start();
        return cts.getResult();
    }

    @Override
    public double getElectricityUsage(Date startDate, Date endDate) {
        CalcTimeStarter cts = new CalcTimeStarter(CalculateTime.ELECTRICITY_USAGE, startDate.getTime(), endDate.getTime());
        cts.start();
        return cts.getResult();
    }

    @Override
    public double getElectricityUsage(Date startDate, Date endDate, int applianceID) {
        CalcTimeStarter cts = new CalcTimeStarter(CalculateTime.ELECTRICITY_USAGE, startDate.getTime(), endDate.getTime(), applianceID);
        cts.start();
        return cts.getResult();
    }

}
