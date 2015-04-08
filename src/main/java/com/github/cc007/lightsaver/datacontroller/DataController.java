/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.cc007.lightsaver.datacontroller;

import com.github.cc007.lightsaver.datacontroller.storage.Entry;
import com.github.cc007.lightsaver.datacontroller.storage.StateLog;
import com.github.cc007.lightsaver.message.rabbitmq.RMQMessageReceiver;
import com.github.cc007.lightsaver.utils.ReferencableMethod;
import com.github.cc007.lightsaver.utils.TransactionHandler;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;

/**
 *
 * @author Rik
 */
public class DataController extends TransactionHandler{

    private final StateLog states;
    private final PersistenceManagerFactory pmf;

    public DataController() {
        this.states = new StateLog("statelog");
        this.pmf = JDOHelper.getPersistenceManagerFactory("StateLog");
    }
    public void addEntry(int clientId, int state, long date){
        handleTransaction(pmf, addEntry, new Object[]{clientId, state, date});
    }

    public ReferencableMethod addEntry = new ReferencableMethod() {

        @Override
        public void execute(Object... args) {
            PersistenceManager pm = (PersistenceManager) args[0];
            Object[] argsArray = (Object[]) (args[1]);
            Entry e = new Entry((Integer) argsArray[0], (Integer) argsArray[1], (Long) argsArray[2]);
            states.getEntries().add(e);
            pm.makePersistent(states);
        }
    };

    public ReferencableMethod getEntries = new ReferencableMethod() {

        @Override
        public void execute(Object... args) {
            PersistenceManager pm = (PersistenceManager) args[0];
            //TODO get entries
        }
    };
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        RMQMessageReceiver asr = new RMQMessageReceiver(new ApplianceStateMessageProtocol(new DataController()));
        asr.start();
        ComputeEngineStarter ces = new ComputeEngineStarter();
        ces.start();
    }

}
