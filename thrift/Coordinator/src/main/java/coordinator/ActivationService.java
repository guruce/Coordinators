package coordinator;

/**
 * Created with IntelliJ IDEA.
 * User: guruce
 * Date: 6/28/13
 * Time: 2:57 PM
 * To change this template use File | Settings | File Templates.
 */
public class ActivationService {

    /**
     * Coordination context identifier for a transaction
     * @return
     */
    public String createCoordinationContext() {
        return TransactionHandler.getInstance().createTransaction();
    }

}
