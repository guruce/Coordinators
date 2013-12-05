package server.activation;

import Utils.CoordinationContext;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 * Created with IntelliJ IDEA.
 * User: Pirinthapan
 * Date: 8/8/13
 * Time: 11:24 PM
 * To change this template use File | Settings | File Templates.
 */
@WebService
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT, use= SOAPBinding.Use.LITERAL)
public interface ActivationService {

    @WebMethod
    CoordinationContext createCoordinationContext(String coordinationType, CoordinationContext importContext, int expires);
}
