/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.cc007.lightsaver.utils;

import com.github.cc007.lightsaver.message.tcp.TCPMessageClient;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;

/**
 *
 * @author Rik
 */
public class TransactionHandler {

    protected void handleTransaction(PersistenceManagerFactory pmf, ReferencableMethod cmd, Object[] args) {

        PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx = pm.currentTransaction();
        try {
            tx.begin();
            cmd.execute(pm, args);
            tx.commit();
            System.out.println("Transaction committed");
        } catch (Exception e) {
            Logger.getLogger(TransactionHandler.class.getName()).log(Level.SEVERE, null, e);
            System.out.println("Exception during transaction: " + e.getMessage());
        } finally {
            if (tx.isActive()) {
                tx.rollback();
            }
            pm.close();
        }
        System.out.println("");
    }
}
