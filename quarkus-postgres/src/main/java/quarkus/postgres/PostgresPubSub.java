package quarkus.postgres;

import io.reactiverse.pgclient.PgConnection;
import io.reactiverse.pgclient.PgPool;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class PostgresPubSub {

    @Inject
    PgPool client;

    // https://eclipse-vertx.github.io/vertx-sql-client/

    @PostConstruct
    public void subscribe() {

        client.getConnection(ar1 -> {

            if (ar1.succeeded()) {

                PgConnection connection = ar1.result();

                connection.notificationHandler(notification -> {
                    System.out.println("Received " + notification.getPayload() + " on channel " + notification.getChannel());
                });

                connection.query("LISTEN mychannel", ar -> {
                    System.out.println("Subscribed to channel");
                });

            } else {
                System.out.println("Could not connect : " + ar1.cause().getMessage());
            }

        });
    }

    public void sendNotification(final String message) {
        client.getConnection(ar1 -> {

            if (ar1.succeeded()) {

                PgConnection connection = ar1.result();

                connection.query("NOTIFY mychannel, '" + message + "'", ar -> {
                    System.out.println("Notified channel");
                });

                connection.close();

            } else {
                System.out.println("Could not notify : " + ar1.cause().getMessage());
            }

        });
    }

}
