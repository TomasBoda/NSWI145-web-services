
package main.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * This class was generated by Apache CXF 3.5.11
 * Thu Mar 27 21:29:08 CET 2025
 * Generated source version: 3.5.11
 */

@XmlRootElement(name = "sendMoney", namespace = "http://main/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "sendMoney", namespace = "http://main/")

public class SendMoney {

    @XmlElement(name = "arg0")
    private java.lang.Double arg0;

    public java.lang.Double getArg0() {
        return this.arg0;
    }

    public void setArg0(java.lang.Double newArg0)  {
        this.arg0 = newArg0;
    }

}

