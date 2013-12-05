package coordinator;

import XAResources.XAResource;
import com.atomikos.icatch.jta.UserTransactionManager;
import server.coordination.CoordinationService;

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
     * @param coordinationService
     * @return
     */
    public boolean register(String transactionID, CoordinationService coordinationService) {

        boolean registerResult = false;

        try {
            Transaction transaction = TransactionHandler.getInstance().getTransaction(transactionID);
            System.out.println(transaction + "this is transactionid " + transactionID);
            UserTransactionManager userTransactionManager = TransactionHandler.getInstance().getUserTransactionManager();

            XAResource xaResource = new XAResource(coordinationService);
            xaResource.setTransactionId(transactionID);

            userTransactionManager.resume(transaction);
            registerResult = transaction.enlistResource(xaResource);
            userTransactionManager.suspend();

            System.out.println("participant registered for " + transactionID);

        } catch (Exception e) {
            System.out.println("regist======"+transactionID + e);
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return registerResult;
    }

}
