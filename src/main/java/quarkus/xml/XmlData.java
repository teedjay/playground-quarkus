package quarkus.xml;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "XmlData")
public class XmlData {
    public String uuid;
    public String message;
}
