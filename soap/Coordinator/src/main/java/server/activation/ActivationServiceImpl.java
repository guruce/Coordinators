package server.activation;

import Utils.Constants;
import Utils.CoordinationContext;
import Utils.EndPointReference;

import javax.jws.WebService;

/**
 * Created with IntelliJ IDEA.
 * User: Pirinthapan
 * Date: 8/9/13
 * Time: 9:19 AM
 * To change this template use File | Settings | File Templates.
 */
@WebService(endpointInterface = "server.activation.ActivationService")
public class ActivationServiceImpl implements ActivationService {

    @Override
    public CoordinationContext createCoordinationContext(final String coordinationType, CoordinationContext importContext, final int expires) {

                System.out.println("activation service called....");

                String transactionId;
                CoordinationContext coordinationContext = new CoordinationContext();
                EndPointReference registrationEPR = new EndPointReference();
                coordinator.ActivationService activationService = new coordinator.ActivationService();

                transactionId = activationService.createCoordinationContext();

                registrationEPR.setAddress(Constants.registrationServiceAddress);
                registrationEPR.setPrivateInstance(Constants.coordinatorServerPortNumber);

                coordinationContext.setIdentifier(transactionId);
                coordinationContext.setCoordinationType(coordinationType);
                coordinationContext.setEndPointReference(registrationEPR);
                coordinationContext.setExpires(expires);

                System.out.println("coordination context created " + coordinationContext);

        return coordinationContext;
    }
}
