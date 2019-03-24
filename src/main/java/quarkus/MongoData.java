package quarkus;

import java.util.List;
import java.util.UUID;

public class MongoData {

    public long count;
    public String uuid = UUID.randomUUID().toString();
    public List<String> names;

}
