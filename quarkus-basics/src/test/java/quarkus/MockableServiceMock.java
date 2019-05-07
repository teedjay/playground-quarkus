package quarkus;

import javax.annotation.Priority;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;

@Alternative()
@Priority(1)
@ApplicationScoped
public class MockableServiceMock extends MockableService {

    @Override
    public String getDescription() {
        return "This text came from the mock service";
    }

}
