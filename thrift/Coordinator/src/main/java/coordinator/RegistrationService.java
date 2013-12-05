package coordinator;

import XAResources.XAResource;
import com.atomikos.icatch.jta.UserTransactionManager;
import org.apache.thrift.async.TAsyncClientManager;

import javax.transaction.Transaction;

/**
 * Created with IntelliJ IDEA.
 * User: guruce
 * Date: 6/28/13
 * Time: 2:58 PM
 * To change this template use File | Settings | File Templates.
 */
public class RegistrationService {

    /**
     * WS-AT registration process
     * @param transactionID
     * @param protocolIdentifier
     * @param participantAddr
     * @param participantPort
     * @return
     */
    public boolean register(String transactionID, String protocolIdentifier, String participantAddr, int participantPort, TAsyncClientManager asyncClientManager) {
        boolean registerResult = false;
        try {

            Transaction transaction = TransactionHandler.getInstance().getTransaction(transactionID);
            UserTransactionManager userTransactionManager = TransactionHandler.getInstance().getUserTransactionManager();

            XAResource xaResource = new XAResource(participantAddr, participantPort, asyncClientManager);
            xaResource.setTransactionId(transactionID);

            userTransactionManager.resume(transaction);
            registerResult = transaction.enlistResource(xaResource);
            userTransactionManager.suspend();

        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return registerResult;
    }

}
