package quarkus;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import java.time.LocalDateTime;

@RequestScoped
public class RequestScoopedBean {

    private String startup;

    @PostConstruct
    public void init() {
        startup = LocalDateTime.now().toString();
    }

    public String getInfoAboutWhenCreated() {
        return "This RequestScoopedBean bean was created @ " + startup;
    }

}
