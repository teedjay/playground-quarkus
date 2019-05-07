package quarkus.kafka;

import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class KafkaTransformer {

    @Incoming("inbound-queue")
    @Outgoing("outbound-queue")
    public String transform(String message) {
        String transformedMessage = new StringBuffer(message).reverse().toString();
        System.out.printf("Transformed : {%s} => {%s}%n", message, transformedMessage);
        return transformedMessage;
    }

}
