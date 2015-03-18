/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.cc007.lightsaver.api;

import java.util.Date;

/**
 *
 * @author Rik
 */
public interface UsageStatistics {

    /**
     * This method can be used to return the number of hours that all lights
     * have been on in a given time interval
     *
     * @param startDate start of the time interval
     * @param endDate end of the time interval
     * @return the number of hours
     */
    public int getHours(Date startDate, Date endDate);

    /**
     * This method can be used to return the number of hours that the lights in
     * a certain room have been on in a given time interval
     *
     * @param startDate start of the time interval
     * @param endDate end of the time interval
     * @param roomId the identifier of the room
     * @return the number of hours
     */
    public int getHours(Date startDate, Date endDate, int roomId);

    /**
     * This method can be used to return the number of seconds that all lights
     * have been on in a given time interval
     *
     * @param startDate start of the time interval
     * @param endDate end of the time interval
     * @return the number of hours
     */
    public int getSeconds(Date startDate, Date endDate);

    /**
     * This method can be used to return the number of seconds that the lights
     * in a certain room have been on in a given time interval
     *
     * @param startDate start of the time interval
     * @param endDate end of the time interval
     * @param roomId the identifier of the room
     * @return the number of seconds
     */
    public int getSeconds(Date startDate, Date endDate, int roomId);

    /**
     * This method can be used to return the amount of electricity that all lights
     * have used in a given time interval
     *
     * @param startDate start of the time interval
     * @param endDate end of the time interval
     * @return the number of hours
     */
    public double getElectricityUsage(Date startDate, Date endDate);

    /**
     * This method can be used to return the amount of electricity that all lights
     * in a certain room have used in a given time interval
     *
     * @param startDate start of the time interval
     * @param endDate end of the time interval
     * @param roomId the identifier of the room
     * @return the number of seconds
     */
    public double getElectricityUsage(Date startDate, Date endDate, int roomId);
}
