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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.jdo.JDOHelper;
import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

/**
 *
 * @author Rik
 */
public class DataController extends TransactionHandler {

    private StateLog states = null;
    private PersistenceManagerFactory pmf;

    public DataController() {
        this.pmf = JDOHelper.getPersistenceManagerFactory("StateLog");
        PersistenceManager pm = pmf.getPersistenceManager();
        try {
            this.states = (StateLog) pm.getObjectById(pm.newObjectIdInstance(StateLog.class, "StateLog"));
            System.out.println("Found existing StateLog");
        } catch (JDOObjectNotFoundException ex) {
            handleTransaction(pmf, initStateLog, new Object[]{"StateLog"});
        }
    }

    public void setPmf(PersistenceManagerFactory pmf) {
        this.pmf = pmf;
    }

    public void addEntry(int clientId, int state, long date) {
        handleTransaction(pmf, addEntry, new Object[]{clientId, state, date});
    }

    public Set<List<Entry>> getEntries(long startDate, long endDate, int appliancId) {
        Set<List<Entry>> returnSet = new HashSet<>();
        PersistenceManager pm = pmf.getPersistenceManager();
        getEntries.execute(pm, new Object[]{startDate, endDate, appliancId, returnSet});
        return returnSet;
    }

    public ReferencableMethod initStateLog = new ReferencableMethod() {

        @Override
        public void execute(Object... args) {
            PersistenceManager pm = (PersistenceManager) args[0];
            Object[] argsArray = (Object[]) (args[1]);
            states = new StateLog((String) argsArray[0]);
            pm.makePersistent(states);
        }
    };

    public ReferencableMethod addEntry = new ReferencableMethod() {

        @Override
        public void execute(Object... args) {
            PersistenceManager pm = (PersistenceManager) args[0];
            Object[] argsArray = (Object[]) (args[1]);
            Entry e = new Entry((Integer) argsArray[0], (Integer) argsArray[1], (Long) argsArray[2]);
            states.getEntries().add(e);
        }
    };

    public ReferencableMethod getEntries = new ReferencableMethod() {

        @Override
        public void execute(Object... args) {
            PersistenceManager pm = (PersistenceManager) args[0];
            Object[] argsArray = (Object[]) (args[1]);
            long startDate = (long) argsArray[0];
            long endDate = (long) argsArray[1];
            int applianceId = (int) argsArray[2];
            Set<List<Entry>> returnSet = (Set<List<Entry>>) argsArray[3];
            Query q;
            if (applianceId == 0) {
                List<Entry> innerList = new ArrayList<>();
                Entry lastEntry = null;
                String queryText = "SELECT * FROM nucleus.entries"
                        + " WHERE `DATE` > '" + startDate
                        + "' AND `DATE` < '" + endDate
                        + "' ORDER BY `CLIENT_ID`, `DATE` ASC";
                System.out.println("\n***********************************\n" + queryText + "\n***********************************\n");
                q = pm.newQuery("javax.jdo.query.SQL", queryText);
                q.setClass(Entry.class);
                List<Entry> entries = (List<Entry>) q.execute();
                Iterator<Entry> iter = entries.iterator();
                if (iter.hasNext()) {
                    Entry e = iter.next();
                    innerList.add(e);
                    lastEntry = e;
                    System.out.println("Entry: " + lastEntry.toString());
                }
                while (iter.hasNext()) {
                    Entry e = iter.next();
                    if (e.getClientId() == lastEntry.getClientId()) {
                        innerList.add(e);
                        lastEntry = e;
                    System.out.println("Entry: " + lastEntry.toString());
                    } else {
                        returnSet.add(innerList);
                        innerList = new ArrayList<>();
                        innerList.add(e);
                        lastEntry = e;
                    System.out.println("Entry: " + lastEntry.toString());
                    }
                }
                returnSet.add(innerList);
            } else {
                List<Entry> innerSet = new ArrayList<>();
                String queryText = "SELECT * FROM nucleus.entries"
                        + " WHERE `DATE` > '" + startDate
                        + "' AND `DATE` < '" + endDate
                        + "' AND `CLIENT_ID` = '" + applianceId
                        + "' ORDER BY `DATE` ASC";
                System.out.println("\n***********************************\n" + queryText + "\n***********************************\n");
                q = pm.newQuery("javax.jdo.query.SQL", queryText);
                q.setClass(Entry.class);
                List<Entry> entries = (List<Entry>) q.execute();
                Iterator<Entry> iter = entries.iterator();
                while (iter.hasNext()) {
                    Entry e = iter.next();
                    innerSet.add(e);
                    System.out.println("Entry: " + e.toString());
                }
                returnSet.add(innerSet);
            }
        }
    };

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DataController dc = new DataController();
        RMQMessageReceiver asr = new RMQMessageReceiver(new ApplianceStateMessageProtocol(dc));
        asr.start();
        System.out.println("Run ComputeEngineStarter");
        ComputeEngineStarter ces = new ComputeEngineStarter(dc);
        ces.start();
        System.out.println("Done running ComputeEngineStarter");
    }

}
