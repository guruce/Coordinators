package client;

import server.coordination.*;

import javax.xml.namespace.QName;
import javax.xml.ws.AsyncHandler;
import javax.xml.ws.Response;
import java.net.URL;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 * Created with IntelliJ IDEA.
 * User: Pirinthapan
 * Date: 11/9/13
 * Time: 11:06 PM
 * To change this template use File | Settings | File Templates.
 */
public class CoordinationAsyncClient {
    CoordinationService completionService;
    String lock = new String();

    public CoordinationAsyncClient(CoordinationService completionService){
        this.completionService = completionService;
    }

    class CommitCallback implements AsyncHandler<CommitResponse> {

        private CommitResponse output;
        public void handleResponse(Response<CommitResponse> response) {
            try {
                output = response.get();
                synchronized (lock){
                    lock.notifyAll();
                }
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        CommitResponse getResponse() {
            return output;
        }
    }


    public ServiceResponse commit(String identifier) throws Exception {

        CommitCallback callbackHandler =
                new CommitCallback();
        Future<?> resp = completionService.commitAsync(identifier, callbackHandler);

        synchronized (lock){
            lock.wait();
        }
        // For the purposes of a test, block until the async call completes
//        resp.get(5L, TimeUnit.MINUTES);

//        while (!resp.isDone()){}

        return callbackHandler.getResponse().getReturn();

//        return completionService.commit(identifier);
    }



    class PrepareCallback implements AsyncHandler<PrepareResponse> {

        private PrepareResponse output;
        public void handleResponse(Response<PrepareResponse> response) {
            try {
                output = response.get();
                synchronized (lock){
                    lock.notifyAll();
                }
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        PrepareResponse getResponse() {
            return output;
        }
    }


    public ServiceResponse prepare(String identifier) throws Exception {


        PrepareCallback callbackHandler =
                new PrepareCallback();
        Future<?> resp = completionService.prepareAsync(identifier, callbackHandler);
        synchronized (lock){
            lock.wait();
        }
        // For the purposes of a test, block until the async call completes
//        resp.get(5L, TimeUnit.MINUTES);

//        while (!resp.isDone()){}

        return callbackHandler.getResponse().getReturn();

//        return completionService.prepare(identifier);
    }


    class RollbackCallback implements AsyncHandler<RollbackResponse> {

        private RollbackResponse output;
        public void handleResponse(Response<RollbackResponse> response) {
            try {
                output = response.get();
                synchronized (lock){
                    lock.notifyAll();
                }
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        RollbackResponse getResponse() {
            return output;
        }
    }


    public ServiceResponse rollback(String identifier) throws Exception {

        RollbackCallback callbackHandler =
                new RollbackCallback();
        Future<?> resp = completionService.rollbackAsync(identifier, callbackHandler);
        synchronized (lock){
            lock.wait();
        }
        // For the purposes of a test, block until the async call completes
//        resp.get(5L, TimeUnit.MINUTES);
//        while (!resp.isDone()){}

        return callbackHandler.getResponse().getReturn();

//        return completionService.rollback(identifier);
    }
}
