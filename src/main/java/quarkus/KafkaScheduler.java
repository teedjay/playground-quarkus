package quarkus;

import io.quarkus.scheduler.Scheduled;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.time.LocalDateTime;

@ApplicationScoped
public class KafkaScheduler {

    @Inject
    KafkaProducer kafkaProducer;

    @Scheduled(every="5s")
    public void schdule() {
        String message = "Here is a new message at " + LocalDateTime.now().toString();
        System.out.printf("Produced : {%s}%n", message);
        kafkaProducer.send(message);
    }

}
