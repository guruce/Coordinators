package Utils;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Created with IntelliJ IDEA.
 * User: Pirinthapan
 * Date: 8/9/13
 * Time: 8:57 AM
 * To change this template use File | Settings | File Templates.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EndPointReference", propOrder = {"address", "privateInstance"})
public class EndPointReference {

    @XmlElement(name = "address", required = true)
    private String address;

    @XmlElement(name = "privateInstance", required = true)
    private int privateInstance;

    public int getPrivateInstance() {
        return privateInstance;
    }

    public void setPrivateInstance(int privateInstance) {
        this.privateInstance = privateInstance;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
