package quarkus;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MockableService {

    public String getDescription() {
        return "This text came from the real implementation - not the mock";
    }

}
