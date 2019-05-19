package quarkus.async;

import io.quarkus.vertx.ConsumeEvent;
import io.vertx.core.json.JsonObject;

import javax.enterprise.context.ApplicationScoped;
import java.time.LocalDateTime;

@ApplicationScoped
public class ConverterService {

    // blocking flag signals that our method will block (and must be run outside of the event loop)

    @ConsumeEvent(value = "convert", blocking = true)
    public JsonObject convertToJson(String message) {
        try { Thread.sleep(1000); } catch (InterruptedException e) { /* nothing */ }
        return new JsonObject()
            .put("message", message)
            .put("thread", Thread.currentThread().getName())
            .put("timestamp", LocalDateTime.now().toString());
    }

}
