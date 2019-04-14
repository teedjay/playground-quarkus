package quarkus.messaging;

import io.quarkus.vertx.ConsumeEvent;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ConsumerTwo {

    @ConsumeEvent("crazy-events")
    public void consume(String name) {
        System.out.printf("Consumer 2 got : %s%n", name.toUpperCase());
    }
    
}
