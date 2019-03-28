package quarkus;

import org.eclipse.microprofile.reactive.messaging.Incoming;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class KafkaConsumer {

    @Incoming("end-queue")
    public void consume(String message) {
        System.out.printf("Consumed : {%s}%n", message);
    }

}
