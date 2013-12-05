package XAResources;

import coordinationclient.CoordinationAsyncClient;
import org.apache.thrift.async.TAsyncClientManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import thriftgen.coordination.ServiceResponse;

import javax.transaction.xa.XAException;
import javax.transaction.xa.Xid;

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
     * @param participantAddress  server address of participant
     * @param participantPort     participant server port number
     */
    public XAResource(String participantAddress, int participantPort, TAsyncClientManager asyncClientManager){
        coordinationAsyncClient = new CoordinationAsyncClient(participantAddress, participantPort, asyncClientManager);
    }

    /**
     *
     * @param xid
     * @param b
     * @throws XAException
     */
    public void commit(Xid xid, boolean b) throws XAException {
        Logger logger = LoggerFactory.getLogger(XAResource.class);
        logger.info("Commit called for " + transactionId);

        System.out.println("Commit called from XA for " + transactionId);
        coordinationAsyncClient.commit(transactionId, b);

        logger.info(transactionId + " committed successfully");
        //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     *
     * @param xid
     * @param i
     * @throws XAException
     */
    public void end(Xid xid, int i) throws XAException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     *
     * @param xid
     * @throws XAException
     */
    public void forget(Xid xid) throws XAException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     *
     * @return
     * @throws XAException
     */
    public int getTransactionTimeout() throws XAException {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     *
     * @param xaResource
     * @return
     * @throws XAException
     */
    public boolean isSameRM(javax.transaction.xa.XAResource xaResource) throws XAException {
        if(xaResource == this)
            return true;
        else
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     *
     * @param xid
     * @return
     * @throws XAException
     */
    public int prepare(Xid xid) throws XAException {
        System.out.println("Prepare called from XA for " + transactionId);

        ServiceResponse pcResponse = coordinationAsyncClient.prepare(transactionId);
        Logger logger = LoggerFactory.getLogger(XAResource.class);
        logger.info("Prepare called for " + transactionId);

        if (pcResponse == ServiceResponse.prepared) {
            logger.info(transactionId + " prepared successfully");
            return XAResource.XA_OK;
        }
        else if(pcResponse == ServiceResponse.readOnly){
            logger.info(transactionId + " Prepare failed");
            return XAResource.XA_RDONLY;
        }
        else throw new XAException();//To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     *
     * @param i
     * @return
     * @throws XAException
     */
    public Xid[] recover(int i) throws XAException {
        return new Xid[0];  //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     *
     * @param xid
     * @throws XAException
     */
    public void rollback(Xid xid) throws XAException {
        System.out.println("rollback called from XA for " + transactionId);
        coordinationAsyncClient.rollback(transactionId);
        //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     *
     * @param i
     * @return
     * @throws XAException
     */
    public boolean setTransactionTimeout(int i) throws XAException {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     *
     * @param xid
     * @param i
     * @throws XAException
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
