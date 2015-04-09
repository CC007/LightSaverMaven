/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.cc007.lightsaver.datacontroller.task;

import com.github.cc007.lightsaver.datacontroller.DataController;
import org.rug.netcomputing.rmi.base.Task;

/**
 *
 * @author Rik
 * @param <T>
 */
public interface DataTask<T> extends Task<T> {

    void setDataController(DataController dc);

}
