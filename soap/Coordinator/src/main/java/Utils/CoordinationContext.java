package Utils;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Created with IntelliJ IDEA.
 * User: Pirinthapan
 * Date: 8/9/13
 * Time: 8:50 AM
 * To change this template use File | Settings | File Templates.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CoordinationContext", propOrder = {"identifier", "coordinationType", "EndPointReference", "expires" })
public class CoordinationContext {

    @XmlElement(name = "identifier", required = true)
    private String identifier;

    @XmlElement(name = "coordinationType", required = true)
    private String coordinationType;

    @XmlElement(name = "EndPointReference", required = true)
    private EndPointReference EndPointReference;

    @XmlElement(name = "expires", required = false)
    private int expires;

    public int getExpires() {
        return expires;
    }

    public void setExpires(int expires) {
        this.expires = expires;
    }

    public EndPointReference getEndPointReference() {
        return EndPointReference;
    }

    public void setEndPointReference(EndPointReference endPointReference) {
        EndPointReference = endPointReference;
    }

    public String getCoordinationType() {
        return coordinationType;
    }

    public void setCoordinationType(String coordinationType) {
        this.coordinationType = coordinationType;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }
}
