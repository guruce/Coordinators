package client;

import server.coordination.CoordinationService;
import server.coordination.CoordinationServiceImplService;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Pirinthapan
 * Date: 12/2/13
 * Time: 7:29 PM
 * To change this template use File | Settings | File Templates.
 */
public class CoordinationClientCache {

    private static final int CACHE_MAX_SIZE = 100;
    private static LinkedHashMap<String, CoordinationService> portCache;

    public CoordinationClientCache(){
        portCache = new LinkedHashMap<String, CoordinationService>(CACHE_MAX_SIZE, 0.75f, true){

            protected boolean removeEldestEntry(
                    Map.Entry<String, CoordinationService> eldest) {
                // Remove the eldest entry if the size of the cache exceeds the
                // maximum size
                return size() > CACHE_MAX_SIZE;
            }
        };

    }

    public CoordinationService getCoordinationServicePort(String address){

        CoordinationService coordinationService = portCache.get(address);

        if(coordinationService != null){
            return coordinationService;
        }
        else {
            coordinationService = createCoordinationServicePort(address);
            portCache.put(address, coordinationService);
            return coordinationService;
        }
    }

    private CoordinationService createCoordinationServicePort(String address){

        CoordinationServiceImplService coordinationServiceImplService = null;
        try {
            coordinationServiceImplService = new CoordinationServiceImplService(new URL(address));
        } catch (MalformedURLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        CoordinationService completionService = coordinationServiceImplService.getCoordinationServiceImplPort();

        return completionService;
    }
}
