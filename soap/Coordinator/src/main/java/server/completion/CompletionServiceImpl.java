package server.completion;

import Utils.Constants;
import Utils.ServiceResponse;
import coordinator.CompletionProtocol;

import javax.jws.WebService;

/**
 * Created with IntelliJ IDEA.
 * User: Pirinthapan
 * Date: 11/8/13
 * Time: 12:42 PM
 * To change this template use File | Settings | File Templates.
 */
@WebService(endpointInterface = "server.completion.CompletionService")
public class CompletionServiceImpl implements CompletionService {
    private static int transactions = 0;
    @Override
    public ServiceResponse.serviceResponse commit(String identifier) {

        System.out.println("Commit called from application for " + identifier);

        CompletionProtocol completionProtocol = new CompletionProtocol();
        ServiceResponse.serviceResponse serviceResponse = completionProtocol.commit(identifier);
        transactions ++;

        System.out.println("Commit finished for " + identifier);
        System.out.println("Total Transactions " + transactions + "..............");

        return serviceResponse;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ServiceResponse.serviceResponse rollBack(String identifier) {

        System.out.println("rollback called for " + identifier);

        CompletionProtocol completionProtocol = new CompletionProtocol();
        ServiceResponse.serviceResponse serviceResponse = completionProtocol.abort(identifier);

        System.out.println("rollback finished for " + identifier);

        return serviceResponse;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
