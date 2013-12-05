package coordinationclient;

import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;
import org.apache.thrift.async.TAsyncClientManager;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.transport.TNonblockingSocket;
import org.apache.thrift.transport.TNonblockingTransport;
import thriftgen.coordination.CoordinationService;
import thriftgen.coordination.ServiceResponse;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Pirinthapan
 * Date: 11/8/13
 * Time: 1:21 AM
 * To change this template use File | Settings | File Templates.
 */
public class CoordinationAsyncClient {
    private String serverUrl;
    private int port;
    private ServiceResponse serviceResponse;
    private String wait = new String();
    private CoordinationService.AsyncClient client;
    TNonblockingTransport tNonblockingTransport;
    TAsyncClientManager asyncClientManager = null;
    TProtocolFactory tProtocolFactory = null;
    private boolean isPrepare = false;

    public CoordinationAsyncClient(String serverUrl, int port, TAsyncClientManager asyncClientManager){
        this.serverUrl = serverUrl;
        this.port = port;
        this.asyncClientManager = asyncClientManager;
    }

    public ServiceResponse commit(String identifier, boolean isOnePhase){

        try {
            if(isOnePhase) {
            tNonblockingTransport = new TNonblockingSocket(serverUrl, port);
            tProtocolFactory = new TBinaryProtocol.Factory();

            client = new CoordinationService.AsyncClient(tProtocolFactory, asyncClientManager, tNonblockingTransport);
            }

            client.commit(identifier, isOnePhase, new CommitCallback());

            synchronized (wait){
                wait.wait();
            }

            tNonblockingTransport.close();
//            asyncClientManager.stop();
            System.out.println("is asynclient manager running after commit " + asyncClientManager.isRunning());

        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (TException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        return serviceResponse;
    }

    class CommitCallback implements AsyncMethodCallback<CoordinationService.AsyncClient.commit_call>{

        @Override
        public void onComplete(CoordinationService.AsyncClient.commit_call commit_call) {
            //To change body of implemented methods use File | Settings | File Templates.
            try {
                serviceResponse = commit_call.getResult();

                synchronized (wait){
                    wait.notifyAll();
                }
            } catch (TException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }

        @Override
        public void onError(Exception e) {
            //To change body of implemented methods use File | Settings | File Templates.
        }
    }


    public ServiceResponse prepare(String identifier){
        isPrepare = true;

        try {
            tNonblockingTransport = new TNonblockingSocket(serverUrl, port);
//            asyncClientManager = new TAsyncClientManager();
            tProtocolFactory = new TBinaryProtocol.Factory();

            client = new CoordinationService.AsyncClient(tProtocolFactory, asyncClientManager, tNonblockingTransport);
            client.prepare(identifier, new PrepareCallback());

            synchronized (wait){
                wait.wait();
            }

        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (TException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        return serviceResponse;
    }

    class PrepareCallback implements AsyncMethodCallback<CoordinationService.AsyncClient.prepare_call>{

        @Override
        public void onComplete(CoordinationService.AsyncClient.prepare_call prepare_call) {
            //To change body of implemented methods use File | Settings | File Templates.
            try {
                serviceResponse = prepare_call.getResult();

                synchronized (wait){
                    wait.notifyAll();
                }
            } catch (TException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }

        @Override
        public void onError(Exception e) {
            //To change body of implemented methods use File | Settings | File Templates.
        }
    }


    public ServiceResponse rollback(String identifier){

        try {

            if(!isPrepare)
                tNonblockingTransport = new TNonblockingSocket(serverUrl, port);

            CoordinationService.AsyncClient client = new CoordinationService.AsyncClient(new TBinaryProtocol.Factory(), asyncClientManager, tNonblockingTransport);
            client.rollback(identifier, new RollbackCallback());

            synchronized (wait){
                wait.wait();
            }
//            asyncClientManager.stop();
        } catch (TException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } finally {
            tNonblockingTransport.close();
        }

        return serviceResponse;
    }

    class RollbackCallback implements AsyncMethodCallback<CoordinationService.AsyncClient.rollback_call>{

        @Override
        public void onComplete(CoordinationService.AsyncClient.rollback_call rollback_call) {
            //To change body of implemented methods use File | Settings | File Templates.
            try {
                serviceResponse = rollback_call.getResult();

                synchronized (wait){
                    wait.notifyAll();
                }
            } catch (TException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }

        @Override
        public void onError(Exception e) {
            //To change body of implemented methods use File | Settings | File Templates.
        }
    }
}
