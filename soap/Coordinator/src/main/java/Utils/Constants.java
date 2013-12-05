package Utils;

/**
 * Created with IntelliJ IDEA.
 * User: Pirinthapan
 * Date: 7/9/13
 * Time: 6:46 PM
 * To change this template use File | Settings | File Templates.
 */
public class Constants {
//    public static final String activationServiceAddress =   "http://10.42.0.1:8081/ActivationService?wsdl";  //"http://10.0.1.4:8081/ActivationService?wsdl";
//    public static final String completionServiceAddress =   "http://10.42.0.1:8082/CompletionService?wsdl";  //"http://10.0.1.4:8082/CompletionService?wsdl";
//    public static final String registrationServiceAddress = "http://10.42.0.1:8083/RegistrationServie?wsdl"; //"http://10.0.1.4:8083/RegistrationServie?wsdl";

    public static String activationServiceAddress =   "http://localhost:8081/ActivationService?wsdl";
    public static String completionServiceAddress =   "http://localhost:8082/CompletionService?wsdl";
    public static String registrationServiceAddress = "http://localhost:8083/RegistrationServie?wsdl";

    public static String coordinationServiceAddress = "http://10.0.1.2:9085/CoordinationService?wsdl";
    public static int coordinatorServerPortNumber = 9090;
}
