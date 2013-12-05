package coordinationclient;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import thriftgen.coordination.CoordinationService;
import thriftgen.coordination.ServiceResponse;

/**
 * Created with IntelliJ IDEA.
 * User: Pirinthapan
 * Date: 7/9/13
 * Time: 5:02 PM
 * To change this template use File | Settings | File Templates.
 * This class will access the thriftgen server in the participant side, which implements the 2pc
 */

public class CoordinationClient {

    String serverUrl;
    int port;
    CoordinationService.Client prepareClient;
    TTransport transport;
    TFramedTransport tFramedTransport;

    /**
     *
     * @param serverUrl server address of the participant
     * @param port      port number of the participant side server
     */
    public CoordinationClient(String serverUrl, int port){
        this.serverUrl = serverUrl;
        this.port = port;
    }

    /**
     *
     * @param identifier transaction identifier
     * @return return whether the commit method success or not
     */
    public ServiceResponse commit(String identifier, boolean isOnePhase){

        CoordinationService.Client commitClient;
        ServiceResponse pcResponse = null;

        try {
            if(isOnePhase){
                  transport = new TSocket(serverUrl, port);
                  transport.open();
            }
                  TProtocol protocol = new TBinaryProtocol(transport);
                  prepareClient = new CoordinationService.Client(protocol);

            pcResponse = prepareClient.commit(identifier, isOnePhase);

            transport.close();

        }catch (TException e) {
            e.printStackTrace();
        }
        return pcResponse;
    }

    /**
     *
     * @param identifier transaction identifier
     * @return      return whether abort method is success or not
     */
    public ServiceResponse abort(String identifier){

        TTransport transport;
        CoordinationService.Client abortClient;
        ServiceResponse pcResponse = null;

        transport = new TSocket(serverUrl, port);
        try {

            transport.open();
            TProtocol protocol = new TBinaryProtocol(transport);
            abortClient = new CoordinationService.Client(protocol);

            pcResponse = abortClient.abort(identifier);

            transport.close();

        }catch (TException e) {
            e.printStackTrace();
        }
        return pcResponse;
    }

    /**
     *
     * @param identifier transaction identifier
     * @return   return whether rollback method is success or not
     */
    public ServiceResponse rollback(String identifier){

        TTransport transport;
        TFramedTransport tFramedTransport;
        CoordinationService.Client rollbackClient;
        ServiceResponse pcResponse = null;

        transport = new TSocket(serverUrl, port);
        try {

            transport.open();
            TProtocol protocol = new TBinaryProtocol(transport);
            rollbackClient = new CoordinationService.Client(protocol);

            pcResponse = rollbackClient.rollback(identifier);

            transport.close();

        }catch (TException e) {
            e.printStackTrace();
        }
        return pcResponse;
    }

    /**
     *
     * @param identifier transaction identifier
     * @return        return whether prepare method is success or not
     */
    public ServiceResponse prepare(String identifier){


        ServiceResponse pcResponse = null;

        transport = new TSocket(serverUrl, port);
        try {

            transport.open();
            TProtocol protocol = new TBinaryProtocol(transport);
            prepareClient = new CoordinationService.Client(protocol);

            pcResponse = prepareClient.prepare(identifier);

//            transport.close();

        }catch (TException e) {
            e.printStackTrace();
        }
        return pcResponse;
    }

}


