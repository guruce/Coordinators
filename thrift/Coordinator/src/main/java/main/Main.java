package main;

import server.activation.ActivationServer;
import server.completion.CompletionServer;
import server.registration.RegistrationServer;
import util.Constants;
import util.XmlLoader;

/**
 * Created with IntelliJ IDEA.
 * User: Pirinthapan
 * Date: 11/2/13
 * Time: 10:40 PM
 * To change this template use File | Settings | File Templates.
 */
public class Main {
    public static void main(String[] args){

        new XmlLoader();

        new Thread(new Runnable() {
            @Override
            public void run() {

                ActivationServer activationServer = new ActivationServer();
                activationServer.startServer(Constants.activationPort);
            }
        }).start();


        new Thread(new Runnable() {
            @Override
            public void run() {

                RegistrationServer registrationServer = new RegistrationServer();
                registrationServer.startServer(Constants.registrationPort);
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {

                CompletionServer completionServer = new CompletionServer();
                completionServer.startServer(Constants.completionPort);
            }
        }).start();
    }
}
