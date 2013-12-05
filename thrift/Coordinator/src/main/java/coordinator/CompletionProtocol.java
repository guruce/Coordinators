package coordinator;

import com.atomikos.icatch.jta.UserTransactionManager;
import thriftgen.completion.ServiceResponse;

import javax.transaction.*;

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
    public ServiceResponse commit(String transactionID) {
        try {

            Transaction transaction = TransactionHandler.getInstance().getTransaction(transactionID);
            UserTransactionManager userTransactionManager = TransactionHandler.getInstance().getUserTransactionManager();

            System.out.println("Commit called to core");
            userTransactionManager.resume(transaction);
            userTransactionManager.commit();
            userTransactionManager.suspend();
            System.out.println("Commit finished in core");

        }
        catch (HeuristicMixedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (HeuristicRollbackException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (RollbackException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (SystemException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (InvalidTransactionException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return ServiceResponse.commited;
    }

    public ServiceResponse abort(String transactionId){

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
        return ServiceResponse.aborted;

    }

}
