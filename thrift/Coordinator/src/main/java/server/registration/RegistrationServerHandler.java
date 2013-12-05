package server.registration;

import org.apache.thrift.async.TAsyncClientManager;
import thriftgen.registration.CoordinationContext;
import thriftgen.registration.EndPointReference;
import thriftgen.registration.RegistrationService;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Pirinthapan
 * Date: 11/2/13
 * Time: 10:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class RegistrationServerHandler implements RegistrationService.Iface {
    private boolean registerReturn = false;
    private String wait = new String();
    TAsyncClientManager asyncClientManager = null;

    public RegistrationServerHandler() {
        try {
            asyncClientManager = new TAsyncClientManager();
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }

    @Override
    public boolean registerParticipant(final CoordinationContext coorContext, final String protocolIdentifier, final EndPointReference participantEPR) {
        System.out.println("Registration called by participant " + participantEPR + "for " + coorContext.getIdentifier() + " " + System.currentTimeMillis()/1000);
        try{
                coordinator.RegistrationService registrationService = new coordinator.RegistrationService();
                registerReturn = registrationService.register(coorContext.getIdentifier(), protocolIdentifier, participantEPR.getAddress(), participantEPR.getPrivateInstance(), asyncClientManager);

        }
        catch (Exception e){
            e.printStackTrace();
        }

        System.out.println("Registration finished for " + participantEPR + " for " + coorContext.getIdentifier());

        return registerReturn;
    }
}
