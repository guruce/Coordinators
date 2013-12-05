package main;

import Utils.XmlLoader;
import server.activation.ActivationServiceImpl;
import server.completion.CompletionServiceImpl;
import server.registration.RegistrationServiceImpl;
import Utils.Constants;

import javax.xml.ws.Endpoint;

/**
 * Created with IntelliJ IDEA.
 * User: Pirinthapan
 * Date: 11/8/13
 * Time: 12:51 PM
 * To change this template use File | Settings | File Templates.
 */
public class Main {
    public static void main(String[] args){

        new XmlLoader();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Activation Service Published on " + Constants.activationServiceAddress);
                Endpoint endpoint = Endpoint.publish(Constants.activationServiceAddress, new ActivationServiceImpl());
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Registration Service Published on " + Constants.registrationServiceAddress);
                Endpoint.publish(Constants.registrationServiceAddress, new RegistrationServiceImpl());
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Completion Service Published on " + Constants.completionServiceAddress);
                Endpoint.publish(Constants.completionServiceAddress, new CompletionServiceImpl());
            }
        }).start();
    }
}
