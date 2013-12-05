
package server.coordination;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for serviceResponse.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="serviceResponse">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="prepared"/>
 *     &lt;enumeration value="committed"/>
 *     &lt;enumeration value="aborted"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(namespace = "serviceResponse")
@XmlEnum
public enum ServiceResponse {

    @XmlEnumValue("prepared")
    PREPARED("prepared"),
    @XmlEnumValue("committed")
    COMMITTED("committed"),
    @XmlEnumValue("aborted")
    ABORTED("aborted");
    private final String value;

    ServiceResponse(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ServiceResponse fromValue(String v) {
        for (ServiceResponse c: ServiceResponse.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
