package quarkus.email;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "MailerStatus")
public class MailerStatus {

    public String to;
    public String subject;
    public List<String> attachments;

}
