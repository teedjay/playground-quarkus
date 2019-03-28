package quarkus;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import java.time.LocalDateTime;

@ApplicationScoped
public class MongoService {

    @ConfigProperty(name = "mongodb.url")
    String mongodbUrl;

    private MongoClient mongoClient;

    @PostConstruct
    public void init() throws Exception {
        mongoClient = new MongoClient(new MongoClientURI(mongodbUrl));
    }

    public String getMongodbUrl() {
        return mongodbUrl;
    }

    public MongoData getMongoData() {
        MongoData data = new MongoData();
        data.names = mongoClient.getDatabaseNames();
        data.count = data.names.size();
        return data;
    }

}
