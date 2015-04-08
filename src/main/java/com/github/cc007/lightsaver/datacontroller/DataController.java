/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.cc007.lightsaver.datacontroller;

import com.github.cc007.lightsaver.datacontroller.storage.StateLog;
import com.github.cc007.lightsaver.message.rabbitmq.RMQMessageReceiver;
import com.github.cc007.lightsaver.utils.ReferencableMethod;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;

/**
 *
 * @author Rik
 */
public class DataController {

    private final StateLog states;

    public DataController() {
        this.states = new StateLog("statelog");
    }

    private void handleTransaction(PersistenceManagerFactory pmf, ReferencableMethod cmd, Object[] args) {

        PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx = pm.currentTransaction();
        try {
            tx.begin();
            cmd.execute(pm, args);
            tx.commit();
            System.out.println("Transaction committed");
        } catch (Exception e) {
            System.out.println("Exception during transaction: " + e.getMessage());
        } finally {
            if (tx.isActive()) {
                tx.rollback();
            }
            pm.close();
        }
        System.out.println("");
    }
    
    public ReferencableMethod storeState = new ReferencableMethod() {

        @Override
        public void execute(Object... args) {
            
            PersistenceManager pm = (PersistenceManager) args[0];
            pm.makePersistent(states);
        }
    };
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        RMQMessageReceiver asr = new RMQMessageReceiver(new ApplianceStateMessageProtocol(new DataController()));
        asr.start();
    }

}
