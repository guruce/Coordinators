package server.activation;

import org.apache.thrift.server.TNonblockingServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TNonblockingServerTransport;
import org.apache.thrift.transport.TTransportException;
import thriftgen.activation.ActivationService;

/**
 * Created with IntelliJ IDEA.
 * User: Pirinthapan
 * Date: 11/2/13
 * Time: 10:15 PM
 * To change this template use File | Settings | File Templates.
 */
public class ActivationServer {

    public void startServer(int port){

        ActivationService.Processor<ActivationServerHandler> processor = new ActivationService.Processor<ActivationServerHandler>(new ActivationServerHandler());

        try {

            TNonblockingServerTransport tNonblockingServerTransport = new TNonblockingServerSocket(port);
            TServer server = new TNonblockingServer(new TNonblockingServer.Args(tNonblockingServerTransport).processor(processor));

           /*TNonblockingServerTransport tNonblockingServerTransport = new TNonblockingServerSocket(port);
            TThreadedSelectorServer.Args args = new TThreadedSelectorServer.Args(tNonblockingServerTransport);
            args.transportFactory(new TFramedTransport.Factory());
            args.protocolFactory(new TBinaryProtocol.Factory());
            args.processor(processor);
            args.selectorThreads(4);
            args.workerThreads(32);
            TServer server = new TThreadedSelectorServer(args);*/

         /*   TServerTransport serverTransport = new TServerSocket(Constants.activationPort);
            TThreadPoolServer.Args args = new TThreadPoolServer.Args(serverTransport);
            args.processor(processor);
            args.maxWorkerThreads(1000);
            TServer server = new TThreadPoolServer(args);*/

            System.out.println("Starting Activation Server on port : " + port);

            server.serve();
        } catch (TTransportException e) {
            System.out.println("Could not Start the Server...");
        }

    }
}
