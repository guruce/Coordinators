package server.completion;

import Utils.ServiceResponse;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 * Created with IntelliJ IDEA.
 * User: Pirinthapan
 * Date: 11/8/13
 * Time: 12:40 PM
 * To change this template use File | Settings | File Templates.
 */
@WebService
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT, use= SOAPBinding.Use.LITERAL)
public interface CompletionService {
    @WebMethod
    ServiceResponse.serviceResponse commit(String identifier);

    @WebMethod
    ServiceResponse.serviceResponse rollBack(String identifier);
}
