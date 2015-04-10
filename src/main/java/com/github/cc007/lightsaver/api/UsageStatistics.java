/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.cc007.lightsaver.api;

import com.github.cc007.lightsaver.datacontroller.task.CalculateTime;
import java.util.Date;

public class UsageStatistics {

    /**
     * This method can be used to return the number of hours that all lights
     * have been on in a given time interval
     *
     * @param startDate start of the time interval
     * @param endDate end of the time interval
     * @return the number of hours
     */
    public static int getHours(Date startDate, Date endDate) {
        CalcTimeStarter cts = new CalcTimeStarter(CalculateTime.HOURS, startDate.getTime(), endDate.getTime());
        cts.start();
        return cts.getResult();
    }

    /**
     * This method can be used to return the number of hours that the lights in
     * a certain room have been on in a given time interval
     *
     * @param startDate start of the time interval
     * @param endDate end of the time interval
     * @param applianceId the identifier of the appliance
     * @return the number of hours
     */
    public static int getHours(Date startDate, Date endDate, int applianceId) {
        CalcTimeStarter cts = new CalcTimeStarter(CalculateTime.HOURS, startDate.getTime(), endDate.getTime(), applianceId);
        cts.start();
        return cts.getResult();
    }

    /**
     * This method can be used to return the number of seconds that all lights
     * have been on in a given time interval
     *
     * @param startDate start of the time interval
     * @param endDate end of the time interval
     * @return the number of hours
     */
    public static int getSeconds(Date startDate, Date endDate) {
        CalcTimeStarter cts = new CalcTimeStarter(CalculateTime.SECONDS, startDate.getTime(), endDate.getTime());
        cts.start();
        return cts.getResult();
    }

    /**
     * This method can be used to return the number of seconds that the lights
     * in a certain room have been on in a given time interval
     *
     * @param startDate start of the time interval
     * @param endDate end of the time interval
     * @param applianceId the identifier of the appliance
     * @return the number of seconds
     */
    public static int getSeconds(Date startDate, Date endDate, int applianceId) {
        CalcTimeStarter cts = new CalcTimeStarter(CalculateTime.SECONDS, startDate.getTime(), endDate.getTime(), applianceId);
        cts.start();
        return cts.getResult();
    }

    /**
     * This method can be used to return the amount of electricity that all
     * lights have used in a given time interval
     *
     * @param startDate start of the time interval
     * @param endDate end of the time interval
     * @return the number of hours
     */
    public static double getElectricityUsage(Date startDate, Date endDate) {
        CalcTimeStarter cts = new CalcTimeStarter(CalculateTime.ELECTRICITY_USAGE, startDate.getTime(), endDate.getTime());
        cts.start();
        return cts.getResult();
    }
/**
     * This method can be used to return the amount of electricity that all lights
     * in a certain room have used in a given time interval
     *
     * @param startDate start of the time interval
     * @param endDate end of the time interval
     * @param applianceId the identifier of the appliance
     * @return the number of seconds
     */
    public static double getElectricityUsage(Date startDate, Date endDate, int applianceId) {
        CalcTimeStarter cts = new CalcTimeStarter(CalculateTime.ELECTRICITY_USAGE, startDate.getTime(), endDate.getTime(), applianceId);
        cts.start();
        return cts.getResult();
    }

}
