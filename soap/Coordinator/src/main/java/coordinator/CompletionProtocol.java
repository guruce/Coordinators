package coordinator;

import Utils.ServiceResponse;
import com.atomikos.icatch.jta.UserTransactionManager;

import javax.transaction.InvalidTransactionException;
import javax.transaction.SystemException;
import javax.transaction.Transaction;

/**
 * Created with IntelliJ IDEA.
 * User: guruce
 * Date: 6/28/13
 * Time: 3:00 PM
 * To change this template use File | Settings | File Templates.
 */
public class CompletionProtocol {

    /**
     * WS-AT completion protocol to starts 2pc protocol
     * @param transactionID
     * @return
     */
    public ServiceResponse.serviceResponse commit(String transactionID) {
        try {

            Transaction transaction = TransactionHandler.getInstance().getTransaction(transactionID);
            UserTransactionManager userTransactionManager = TransactionHandler.getInstance().getUserTransactionManager();

            userTransactionManager.resume(transaction);
            userTransactionManager.commit();
            userTransactionManager.suspend();

        } catch (Exception e){
            e.printStackTrace();
        }

       /* catch (HeuristicMixedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (HeuristicRollbackException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (RollbackException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (SystemException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }*/
        return ServiceResponse.serviceResponse.committed;
    }

    public ServiceResponse.serviceResponse abort(String transactionId){

        Transaction transaction = TransactionHandler.getInstance().getTransaction(transactionId);
        UserTransactionManager userTransactionManager = TransactionHandler.getInstance().getUserTransactionManager();

        try {
            userTransactionManager.resume(transaction);
            userTransactionManager.rollback();
            userTransactionManager.suspend();

        } catch (SystemException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (InvalidTransactionException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return ServiceResponse.serviceResponse.aborted;

    }

}
