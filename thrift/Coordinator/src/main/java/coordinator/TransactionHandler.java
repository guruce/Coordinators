package coordinator;

import com.atomikos.icatch.jta.UserTransactionManager;

import javax.transaction.NotSupportedException;
import javax.transaction.SystemException;
import javax.transaction.Transaction;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: guruce
 * Date: 6/28/13
 * Time: 1:51 PM
 * To change this template use File | Settings | File Templates.
 */
@SuppressWarnings("JavaDoc")
public class TransactionHandler {

    // Create a hash map to store transaction objects
    private HashMap transactionMap;
    private UserTransactionManager userTransactionManager;
    private TransactionHandler transactionHandler;
    private static TransactionHandler instance = new TransactionHandler();

    /**
     * Create singleton instance for this class
     * @return TransactionHandler
     */
    public synchronized static TransactionHandler getInstance() {
        return instance;
    }

    /**
     * Initiate Atomikos and hashMap
     */
    private TransactionHandler() {
        userTransactionManager = new UserTransactionManager();
        transactionMap = new HashMap();
    }

    /**
     * Create Transaction object for single multiple transaction and put it into map and return map key
     * @return String transactionID
     */
    public String createTransaction() {

        try {
            userTransactionManager.begin();
            Transaction transaction = userTransactionManager.getTransaction();
            String transactionID = transaction.toString();
            transactionMap.put(transactionID, transaction);
            userTransactionManager.suspend();
            return transactionID;

        } catch (SystemException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            return null;
        } catch (NotSupportedException e) {
            e.printStackTrace();
            return null; //To change body of catch statement use File | Settings | File Templates.
        }
    }

    /**
     * Push transaction object from map with key
     * @param transactionID
     * @return Transaction
     */
    public Transaction getTransaction(String transactionID) {
        return (Transaction) transactionMap.get(transactionID);
    }

    public UserTransactionManager getUserTransactionManager(){
        return userTransactionManager;
    }

}
