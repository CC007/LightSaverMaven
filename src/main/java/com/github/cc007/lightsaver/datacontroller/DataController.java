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

    public Set<Entry> getEntries(long startDate, long endDate, int appliancId) {
        Set<Entry> returnSet = new HashSet<>();
        handleTransaction(pmf, getEntries, new Object[]{startDate, endDate, appliancId, returnSet});
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
            Set<Entry> returnSet = (Set<Entry>) argsArray[3];
            Query q;
            if (applianceId == 0) {
                q = pm.newQuery("SELECT FROM " + Entry.class.getName()
                        + " WHERE date > " + startDate + " AND date < " + endDate + " ORDER BY date ASC");
            } else {
                q = pm.newQuery("SELECT FROM " + Entry.class.getName()
                        + " WHERE CLIENTID = " + applianceId + " AND date > " + startDate + " AND date < " + endDate + " ORDER BY date ASC");
            }
            List<Entry> products = (List<Entry>) q.execute();
            Iterator<Entry> iter = products.iterator();
            while (iter.hasNext()) {
                Entry p = iter.next();
                returnSet.add(p);
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
        ComputeEngineStarter ces = new ComputeEngineStarter(dc);
        ces.start();
    }

}
