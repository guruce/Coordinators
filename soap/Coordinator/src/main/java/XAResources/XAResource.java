package XAResources;

import client.CoordinationAsyncClient;
import server.coordination.CoordinationService;
import server.coordination.ServiceResponse;

import javax.transaction.xa.XAException;
import javax.transaction.xa.Xid;
import javax.xml.namespace.QName;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created with IntelliJ IDEA.
 * User: pirinthapan
 * Date: 6/13/13
 * Time: 9:21 PM
 * To change this template use File | Settings | File Templates.
 * this class implements the XAResource interface and all the methods implemented here will be used by atomikos
 */
public class XAResource implements javax.transaction.xa.XAResource {

    private CoordinationAsyncClient coordinationAsyncClient;
    private String transactionId;

    /**
     *
     * @param coordinationService  server address of participant
     */
    public XAResource(CoordinationService coordinationService){
        coordinationAsyncClient = new CoordinationAsyncClient(coordinationService);
    }

    /**
     *
     * @param xid
     * @param b
     * @throws javax.transaction.xa.XAException
     */
    public void commit(Xid xid, boolean b) throws XAException {
        System.out.println("commit called");
        try {
            coordinationAsyncClient.commit(transactionId);
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     *
     * @param xid
     * @param i
     * @throws javax.transaction.xa.XAException
     */
    public void end(Xid xid, int i) throws XAException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     *
     * @param xid
     * @throws javax.transaction.xa.XAException
     */
    public void forget(Xid xid) throws XAException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     *
     * @return
     * @throws javax.transaction.xa.XAException
     */
    public int getTransactionTimeout() throws XAException {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     *
     * @param xaResource
     * @return
     * @throws javax.transaction.xa.XAException
     */
    public boolean isSameRM(javax.transaction.xa.XAResource xaResource) throws XAException {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     *
     * @param xid
     * @return
     * @throws javax.transaction.xa.XAException
     */
    public int prepare(Xid xid) throws XAException {
        System.out.println("Prepare successfully called");

        ServiceResponse serviceResponse = null;
        try {
            serviceResponse = coordinationAsyncClient.prepare(transactionId);
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        if (serviceResponse == ServiceResponse.PREPARED) {
            return XAResource.XA_OK;
        }
        else if(serviceResponse == ServiceResponse.ABORTED)
            return XAResource.XA_RDONLY;
        else throw new XAException();

    }

    /**
     *
     * @param i
     * @return
     * @throws javax.transaction.xa.XAException
     */
    public Xid[] recover(int i) throws XAException {
        return new Xid[0];  //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     *
     * @param xid
     * @throws javax.transaction.xa.XAException
     */
    public void rollback(Xid xid) throws XAException {
        try {
            coordinationAsyncClient.rollback(transactionId);
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     *
     * @param i
     * @return
     * @throws javax.transaction.xa.XAException
     */
    public boolean setTransactionTimeout(int i) throws XAException {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     *
     * @param xid
     * @param i
     * @throws javax.transaction.xa.XAException
     */
    public void start(Xid xid, int i) throws XAException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     *
     * @return
     */
    public String getTransactionId() {
        return transactionId;
    }

    /**
     *
     * @param transactionId
     */
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
}
