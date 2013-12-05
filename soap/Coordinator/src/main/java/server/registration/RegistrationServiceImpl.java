package server.registration;

import Utils.Constants;
import Utils.CoordinationContext;
import Utils.EndPointReference;
import client.CoordinationClientCache;
import server.coordination.CoordinationService;

import javax.jws.WebService;

/**
 * Created with IntelliJ IDEA.
 * User: Pirinthapan
 * Date: 11/8/13
 * Time: 12:43 PM
 * To change this template use File | Settings | File Templates.
 */
@WebService(endpointInterface = "server.registration.RegistrationService")
public class RegistrationServiceImpl implements RegistrationService {
    CoordinationClientCache coordinationClientCache = new CoordinationClientCache();
    CoordinationService coordinationService;

    @Override
    public boolean registerParticipant(String identifier, String protocolIdentifier, String participantAddress, int participantPort) {
        System.out.println("registration called .......................");

        coordinator.RegistrationService registrationService = new coordinator.RegistrationService();
        coordinationService = coordinationClientCache.getCoordinationServicePort(participantAddress);

        return registrationService.register(identifier, coordinationService);  //To change body of implemented methods use File | Settings | File Templates.
    }
}
