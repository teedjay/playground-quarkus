package quarkus.kafka;

import org.eclipse.microprofile.reactive.messaging.Outgoing;

import javax.enterprise.context.ApplicationScoped;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.LinkedBlockingQueue;

@ApplicationScoped
public class KafkaProducer {

    private BlockingQueue<String> messages = new LinkedBlockingQueue<>();

    @Outgoing("start-queue")
    public CompletionStage<String> producer() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return messages.take();
            } catch (InterruptedException e) {
                throw new RuntimeException("Production halted : " + e.getMessage());
            }
        });
    };

    public void send(String message) {
        messages.add(message);
    }

}
