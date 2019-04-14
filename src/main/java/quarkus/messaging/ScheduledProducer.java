package quarkus.messaging;

import io.quarkus.scheduler.Scheduled;
import io.vertx.core.eventbus.EventBus;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.time.LocalDateTime;

@ApplicationScoped
public class ScheduledProducer {

    @Inject
    EventBus eventBus;

    @Scheduled(every="5s")
    public void broadcast() {
        String message = "Crazy data from ScheduledProducer is " + LocalDateTime.now().toString();
        eventBus.publish("crazy-events", message);
    }

}
