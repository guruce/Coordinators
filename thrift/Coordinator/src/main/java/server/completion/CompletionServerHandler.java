package server.completion;

import coordinator.CompletionProtocol;
import org.apache.thrift.TException;
import thriftgen.completion.CompletionService;
import thriftgen.completion.ServiceResponse;

/**
 * Created with IntelliJ IDEA.
 * User: Pirinthapan
 * Date: 11/2/13
 * Time: 10:26 PM
 * To change this template use File | Settings | File Templates.
 */
public class CompletionServerHandler implements CompletionService.Iface {
    private static int transactions = 0;

    @Override
    public ServiceResponse commit(String txIdentifier) throws TException {
        System.out.println("commit called from application for " + txIdentifier + " " + System.currentTimeMillis()/1000);
        CompletionProtocol completionProtocol = new CompletionProtocol();
        ServiceResponse serviceResponse = completionProtocol.commit(txIdentifier);
        System.out.println("commit finished for " + txIdentifier);

        transactions ++;

        System.out.println("Number of Transactions " + transactions + ".................................................");

        return serviceResponse;
    }

    @Override
    public ServiceResponse rollBack(String txIdentifier) throws TException {
        System.out.println("rollback called by application for " + txIdentifier + " " + System.currentTimeMillis()/1000);
        CompletionProtocol completionProtocol = new CompletionProtocol();
        ServiceResponse serviceResponse = completionProtocol.abort(txIdentifier);
        System.out.println("rollback finished for " + txIdentifier);

        return serviceResponse;
    }
}
