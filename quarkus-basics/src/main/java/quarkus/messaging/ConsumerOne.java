package quarkus.messaging;

import io.quarkus.vertx.ConsumeEvent;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ConsumerOne {

    @ConsumeEvent("crazy-events")
    public void consume(String name) {
        System.out.printf("Consumer 1 got : %s%n", name.toLowerCase());
    }

}
